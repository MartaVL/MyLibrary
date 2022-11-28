package com.example.mylibrary.model

import com.example.mylibrary.data.Book

class LibraryModel {
   companion object {
       //Conexión con base de datos
       fun loadAllBooks(): MutableList<Book> {
           return mutableListOf(
               Book(name = "Una corte de rosas y espinas", author ="Sarah J. Mass", genre = "Fantasia"),
               Book(name = "De Sangre y cenizas", author = "Jennifer L. Armentrout", genre = "Fantasia"),
           )
       }

       fun loadLoansBooks(books: MutableList<Book>?) :  MutableList<Book> {
           var loansBooks: MutableList<Book> = mutableListOf()

           for(book in books!!) {
                if(book.loan) {
                    loansBooks.add(book)
                }
           }

           return loansBooks
       }

       fun loadNoReadBooks(books: MutableList<Book>?) :  MutableList<Book> {
           var noReadsBooks: MutableList<Book> = mutableListOf()

           for(book in books!!) {
               if(!book.read) {
                   noReadsBooks.add(book)
               }
           }

           return noReadsBooks
       }

       fun getBook(position: Int, books: MutableList<Book>?) : Book? {
           for(i in 0 until books!!.size) {
               if(i == position) {
                   return books[i]
               }
           }

           return null
       }



       //Conexión base de datos
       fun saveBook(position: Int, isRead: Boolean, isLoan: Boolean, score: Int, books: MutableList<Book>?) : MutableList<Book> {
           for(i in 0 until books!!.size) {
               if(i == position) {
                   books[i].read = isRead
                   books[i].loan = isLoan
                   books[i].score = score
               }
           }

           return books
       }
   }


}