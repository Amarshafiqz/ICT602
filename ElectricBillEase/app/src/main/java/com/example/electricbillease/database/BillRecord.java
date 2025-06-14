package com.example.electricbillease.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BillRecord {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String month;
    public int units;
    public int rebate;
    public double totalCharges;
    public double finalCost;

    public BillRecord(String month, int units, int rebate, double totalCharges, double finalCost) {
        this.month = month;
        this.units = units;
        this.rebate = rebate;
        this.totalCharges = totalCharges;
        this.finalCost = finalCost;
    }
}
