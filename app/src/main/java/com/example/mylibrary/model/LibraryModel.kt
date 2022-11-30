package com.example.mylibrary.model

import android.app.Application
import android.content.Context
import com.example.mylibrary.database.Book
import com.example.mylibrary.database.BookDao
import com.example.mylibrary.database.LibraryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class LibraryModel () {

    private lateinit var bookDao: BookDao

    suspend fun connectDatabase(context: Context) {
        return withContext(Dispatchers.IO) {
            bookDao = LibraryDatabase.getInstance(context)?.bookDao()!!

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

    suspend fun getLoanBooks() : MutableList<Book>? {
        return withContext(Dispatchers.IO) {
            bookDao?.getLoanBooks()
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