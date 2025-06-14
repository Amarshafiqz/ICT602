package com.example.electricbillease.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BillDao {

    @Insert
    void insert(BillRecord record);

    @Query("SELECT * FROM BillRecord ORDER BY id DESC")
    List<BillRecord> getAll();

    @Query("SELECT * FROM BillRecord WHERE id = :id")
    BillRecord getById(int id);

    @Delete
    void delete(BillRecord record);

}
