package com.alpheaus.databaseapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface AccountDao {
    @Query("select * from accounts")
    List<Account> getAllAccounts();

    @Query("select * from accounts where accNumber=:accNumber")
    Account getOneAccount(String accNumber);

    @Query("delete from accounts where accNumber=:accNumber")
    void deleteAccount(String accNumber);

    @Insert
    void insertAccount(Account x);

    @Update
    void updateAccount(Account z);

    @Delete
    void delete(Account k);
}
