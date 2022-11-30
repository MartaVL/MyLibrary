package com.example.mylibrary.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.database.Book
import com.example.mylibrary.model.LibraryModel
import kotlinx.coroutines.launch

class LibraryViewModel(): ViewModel() {

    private val model: LibraryModel = LibraryModel()

    val allBooks = MutableLiveData<MutableList<Book>>()
    val noReadBooks = MutableLiveData<MutableList<Book>>()
    val loansBooks = MutableLiveData<MutableList<Book>>()

    fun onCreate(context: Context) {
        viewModelScope.launch {
            model.connectDatabase(context)
            allBooks.postValue(model.getAllBooks())
            noReadBooks.postValue(model.getNoReadBooks())
            loansBooks.postValue(model.getLoanBooks())
        }
    }

    fun getData(position: Int) : Book? {
        for(i in 0 until allBooks.value!!.size) {
            if (i == position) {
                return allBooks.value!![i]
            }
        }
        return null
    }

    fun saveData(position: Int, isRead: Boolean, isLoan: Boolean, score: Int) {
        viewModelScope.launch {
            var book: Book? = null
            for (i in 0 until allBooks.value!!.size) {
                if (i == position) {
                    book = allBooks.value!![i]
                    book.read = isRead
                    book.loan = isLoan
                    book.score = score
                    break
                }
            }

            if(book != null) {
                model.saveBook(book)
                allBooks.postValue(model.getAllBooks())
                noReadBooks.postValue(model.getNoReadBooks())
                loansBooks.postValue(model.getLoanBooks())
            }

        }
    }
}