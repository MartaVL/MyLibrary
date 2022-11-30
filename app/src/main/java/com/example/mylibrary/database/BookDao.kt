package com.example.mylibrary.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getAllBooks(): MutableList<Book>

    @Query("SELECT * FROM books WHERE read = 0")
    fun getNoReadBooks(): MutableList<Book>

    @Query("SELECT * FROM books WHERE loan = 1")
    fun getLoanBooks(): MutableList<Book>

    @Insert
    fun addBook(book: Book)

    @Delete
    fun deleteBook(vararg book: Book)

    @Update
    fun saveBook(vararg book: Book)

}