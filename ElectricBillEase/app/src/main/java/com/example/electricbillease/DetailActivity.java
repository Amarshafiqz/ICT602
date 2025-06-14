package com.example.electricbillease;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.electricbillease.database.AppDatabase;
import com.example.electricbillease.database.BillRecord;

public class DetailActivity extends AppCompatActivity {

    TextView textMonth, textUnits, textRebate, textTotal, textFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textMonth = findViewById(R.id.textMonth);
        textUnits = findViewById(R.id.textUnits);
        textRebate = findViewById(R.id.textRebate);
        textTotal = findViewById(R.id.textTotal);
        textFinal = findViewById(R.id.textFinal);

        int billId = getIntent().getIntExtra("bill_id", -1);
        if (billId != -1) {
            BillRecord bill = AppDatabase.getInstance(this).billDao().getById(billId);
            if (bill != null) {
                textMonth.setText("Month: " + bill.month);
                textUnits.setText("Units: " + bill.units + " kWh");
                textRebate.setText("Rebate: " + bill.rebate + "%");
                textTotal.setText(String.format("Total Charges: RM %.2f", bill.totalCharges));
                textFinal.setText(String.format("Final Cost: RM %.2f", bill.finalCost));
            }
        }
    }
}
