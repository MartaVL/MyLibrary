package com.example.mylibrary.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface LoanDao {
    @Query("SELECT * FROM loans")
    fun getAllLoans(): MutableList<Loan>

    @Insert
    fun addLoan(loan: Loan)

    @Delete
    fun deleteLoan(vararg loan: Loan)

}