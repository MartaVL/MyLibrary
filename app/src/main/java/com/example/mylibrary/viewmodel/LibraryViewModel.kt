package com.example.mylibrary.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mylibrary.database.Book
import com.example.mylibrary.database.Loan
import com.example.mylibrary.model.LibraryModel
import kotlinx.coroutines.launch
import java.util.*

class LibraryViewModel(): ViewModel() {

    private val model: LibraryModel = LibraryModel()

    val allBooks = MutableLiveData<MutableList<Book>>()
    val noReadBooks = MutableLiveData<MutableList<Book>>()
    val loansBooks = MutableLiveData<MutableList<Loan>>()

    fun onCreate(context: Context) {
        viewModelScope.launch {
            model.connectDatabase(context)

            /*val books = model.getAllBooks()

            if(books != null && books.size == 0) {
                val book = Book("Una corte de rosas y espinas", "Sarah J. Maas", "Fantasia")
                book.loan = true
                model.addBook(book)
                model.addBook(Book("De Sangre y Cenizas", "Jennifer L. Armentrout", "Fantasia"))

                model.addLoan(Loan(1, "anonimo", Date(Calendar.getInstance().timeInMillis)))
            }*/

            allBooks.postValue(model.getAllBooks() ?: mutableListOf())
            noReadBooks.postValue(model.getNoReadBooks() ?: mutableListOf())
            updateLoans()
        }
    }

    private suspend fun updateLoans() {
        val loans = model.getLoanBooks() ?: mutableListOf()
        for(loan in loans) {
            loan.nameBook = getBook(loan.idBook)?.name ?: ""
        }
        loansBooks.postValue(loans)
    }

    fun getBook(id: Int) : Book? {
        for(book in allBooks.value!!) {
            if (book.id == id) {
                return book
            }
        }
        return null
    }

    fun getLoan(id: Int) : Loan? {
        for(loan in loansBooks.value!!) {
            if (loan.id == id) {
                return loan
            }
        }
        return null
    }

    fun returnLoan(id: Int) {
        viewModelScope.launch {
            for(loan in loansBooks.value!!) {
                if(loan.id == id) {
                    model.deleteLoan(loan)
                    val book = getBook(loan.idBook)
                    book?.let {
                        saveBook(it.id, it.read, false, it.score, "")
                    }
                }
            }
        }
    }

    fun saveBook(id: Int, isRead: Boolean, isLoan: Boolean, score: Int, lentTo: String) {
        viewModelScope.launch {
            val book = getBook(id)
            book?.let {
                it.read = isRead
                it.loan = isLoan
                it.score = score
                model.saveBook(it)

                if(it.loan) {
                    model.addLoan(Loan(it.id, lentTo, Date(Calendar.getInstance().timeInMillis)))
                }

                allBooks.postValue(model.getAllBooks())
                noReadBooks.postValue(model.getNoReadBooks())
                updateLoans()
            }
        }
    }
}