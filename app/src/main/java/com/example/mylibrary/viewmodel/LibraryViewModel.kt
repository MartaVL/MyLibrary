package com.example.mylibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylibrary.data.Book
import com.example.mylibrary.model.LibraryModel

class LibraryViewModel: ViewModel() {
    val allBooks = MutableLiveData<MutableList<Book>>()
    val noReadBooks = MutableLiveData<MutableList<Book>>()
    val loansBooks = MutableLiveData<MutableList<Book>>()

    fun loadData() {
        var books = LibraryModel.loadAllBooks()
        allBooks.postValue(books)
        noReadBooks.postValue(LibraryModel.loadNoReadBooks(books))
        loansBooks.postValue(LibraryModel.loadLoansBooks(books))
    }

    fun getData(position: Int) : Book? {
        return LibraryModel.getBook(position, allBooks.value)
    }

    fun saveData(position: Int, isRead: Boolean, isLoan: Boolean, score: Int) {
        var books = LibraryModel.saveBook(position, isRead, isLoan, score, allBooks.value)
        allBooks.postValue(books)
        noReadBooks.postValue(LibraryModel.loadNoReadBooks(books))
        loansBooks.postValue(LibraryModel.loadLoansBooks(books))
    }
}