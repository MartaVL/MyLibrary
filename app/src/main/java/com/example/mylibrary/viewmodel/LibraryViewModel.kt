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
        allBooks.postValue(LibraryModel.loadAllBooks())
    }

    fun loadData2() {
        noReadBooks.postValue(LibraryModel.loadNoReadBooks(allBooks.value))
    }

    fun loadData3() {
        loansBooks.postValue(LibraryModel.loadLoansBooks( allBooks.value))
    }

    fun getData(position: Int) : Book? {
        return LibraryModel.getBook(position, allBooks.value)
    }

    fun saveData(position: Int, isRead: Boolean, isLoan: Boolean, score: Int) {
        allBooks.postValue(LibraryModel.saveBook(position, isRead, isLoan, score, allBooks.value))
        noReadBooks.postValue(LibraryModel.loadNoReadBooks(allBooks.value))
        loansBooks.postValue(LibraryModel.loadLoansBooks( allBooks.value))
    }
}