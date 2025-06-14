package com.example.electricbillease;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import com.example.electricbillease.database.AppDatabase;
import com.example.electricbillease.database.BillRecord;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    ListView listViewHistory;
    List<BillRecord> billList;
    ArrayAdapter<String> adapter;
    String[] items;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listViewHistory = findViewById(R.id.listViewHistory);
        db = AppDatabase.getInstance(this);

        loadData();

        // Click to view details
        listViewHistory.setOnItemClickListener((parent, view, position, id) -> {
            BillRecord selected = billList.get(position);
            Intent intent = new Intent(HistoryActivity.this, DetailActivity.class);
            intent.putExtra("bill_id", selected.id);
            startActivity(intent);
        });

        // Long click to delete
        listViewHistory.setOnItemLongClickListener((parent, view, position, id) -> {
            BillRecord toDelete = billList.get(position);

            new AlertDialog.Builder(HistoryActivity.this)
                    .setTitle("Delete Bill")
                    .setMessage("Are you sure you want to delete this bill record?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        db.billDao().delete(toDelete);
                        loadData(); // refresh
                        Toast.makeText(this, "Bill deleted.", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();

            return true;
        });
    }

    private void loadData() {
        billList = db.billDao().getAll();
        items = new String[billList.size()];
        for (int i = 0; i < billList.size(); i++) {
            BillRecord b = billList.get(i);
            items[i] = b.month + ": RM " + String.format("%.2f", b.finalCost);
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listViewHistory.setAdapter(adapter);
    }
}
