package com.example.mylibrary.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mylibrary.data.Book
import com.example.mylibrary.model.LibraryModel

class LibraryViewModel: ViewModel() {
    val libraryModel = MutableLiveData<MutableList<Book>>()

    fun loadData() {
        libraryModel.postValue(LibraryModel.loadBooks())
    }

    fun getData(position: Int) : String {
        return LibraryModel.getBook(position, libraryModel.value)
    }
}