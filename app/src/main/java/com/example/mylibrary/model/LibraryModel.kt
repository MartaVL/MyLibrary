package com.example.mylibrary.model

import android.content.Context
import com.example.mylibrary.database.*
//import com.example.mylibrary.database.LoanDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LibraryModel () {

    private lateinit var bookDao: BookDao
    private lateinit var loanDao: LoanDao

    suspend fun connectDatabase(context: Context) {
        return withContext(Dispatchers.IO) {
            var database = LibraryDatabase.getInstance(context)
            bookDao = database?.bookDao()!!
            loanDao = database?.loanDao()!!
        }
    }

    suspend fun getAllBooks() : MutableList<Book>? {
        return withContext(Dispatchers.IO) {
            bookDao?.getAllBooks()
        }
    }

    suspend fun getNoReadBooks() : MutableList<Book>? {
        return withContext(Dispatchers.IO) {
            bookDao?.getNoReadBooks()
        }
    }

    suspend fun getLoanBooks() : MutableList<Loan>? {
        return withContext(Dispatchers.IO) {
            loanDao?.getAllLoans()
        }
    }

    suspend fun addLoan(loan: Loan) {
        return withContext(Dispatchers.IO) {
            loanDao?.addLoan(loan)
        }
    }

    suspend fun deleteLoan(loan: Loan) {
        return withContext(Dispatchers.IO) {
            loanDao?.deleteLoan(loan)
        }
    }

    suspend fun addBook(book: Book) {
        return withContext(Dispatchers.IO) {
            bookDao?.addBook(book)
        }
    }

    suspend fun deleteBook(book: Book) {
        return withContext(Dispatchers.IO) {
            bookDao?.deleteBook(book)
        }
    }

    suspend fun saveBook(book: Book) {
        return withContext(Dispatchers.IO) {
            bookDao?.saveBook(book)
        }
    }
}