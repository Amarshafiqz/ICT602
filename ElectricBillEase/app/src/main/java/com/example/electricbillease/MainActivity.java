package com.example.electricbillease;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.electricbillease.database.AppDatabase;
import com.example.electricbillease.database.BillRecord;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerMonth;
    EditText editTextUnits;
    SeekBar seekBarRebate;
    TextView textViewRebateValue, textViewTotalCharges, textViewFinalCost;
    Button buttonCalculate, buttonSave, buttonViewHistory, buttonAbout, buttonInstruction;

    int rebate = 0;
    double totalCharges = 0;
    double finalCost = 0;

    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        db = AppDatabase.getInstance(this);

        // Link UI
        spinnerMonth = findViewById(R.id.spinnerMonth);
        editTextUnits = findViewById(R.id.editTextUnits);
        seekBarRebate = findViewById(R.id.seekBarRebate);
        textViewRebateValue = findViewById(R.id.textViewRebateValue);
        textViewTotalCharges = findViewById(R.id.textViewTotalCharges);
        textViewFinalCost = findViewById(R.id.textViewFinalCost);
        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonSave = findViewById(R.id.buttonSave);
        buttonViewHistory = findViewById(R.id.buttonViewHistory);
        buttonAbout = findViewById(R.id.buttonAbout);
        buttonInstruction = findViewById(R.id.buttonInstruction);

        // Month spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.months_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonth.setAdapter(adapter);

        // Rebate seek bar
        seekBarRebate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                rebate = progress;
                textViewRebateValue.setText("Rebate: " + rebate + "%");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {}
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Calculate button
        buttonCalculate.setOnClickListener(v -> {
            try {
                int units = Integer.parseInt(editTextUnits.getText().toString());
                totalCharges = calculateCharges(units);
                finalCost = totalCharges - (totalCharges * rebate / 100.0);
                textViewTotalCharges.setText("Total Charges: RM " + String.format("%.2f", totalCharges));
                textViewFinalCost.setText("Final Cost: RM " + String.format("%.2f", finalCost));
            } catch (Exception e) {
                Toast.makeText(this, "Please enter valid units", Toast.LENGTH_SHORT).show();
            }
        });

        // Save button
        buttonSave.setOnClickListener(v -> {
            try {
                String month = spinnerMonth.getSelectedItem().toString();
                int units = Integer.parseInt(editTextUnits.getText().toString());
                BillRecord record = new BillRecord(month, units, rebate, totalCharges, finalCost);
                db.billDao().insert(record);
                Toast.makeText(this, "Saved to history!", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Please calculate the bill first", Toast.LENGTH_SHORT).show();
            }
        });

        // History screen
        buttonViewHistory.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, HistoryActivity.class)));

        // About screen
        buttonAbout.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, AboutActivity.class)));

        // Instruction screen
        buttonInstruction.setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this, InstructionActivity.class)));
    }

    // Tariff Calculation Logic
    private double calculateCharges(int units) {
        double total = 0;
        if (units <= 200) {
            total = units * 0.218;
        } else if (units <= 300) {
            total = 200 * 0.218 + (units - 200) * 0.334;
        } else if (units <= 600) {
            total = 200 * 0.218 + 100 * 0.334 + (units - 300) * 0.516;
        } else {
            total = 200 * 0.218 + 100 * 0.334 + 300 * 0.516 + (units - 600) * 0.546;
        }
        return total;
    }
}
