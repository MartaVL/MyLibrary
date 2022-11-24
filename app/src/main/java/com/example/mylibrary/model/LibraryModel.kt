package com.example.mylibrary.model

import com.example.mylibrary.data.Book

class LibraryModel {
   companion object {
       fun loadBooks(): MutableList<Book> {
           return mutableListOf(
               Book(name = "Una corte de rosas y espinas", author ="Sarah J. Mass", genre = "Fantasia"),
               Book(name = "De Sangre y cenizas", author = "Jennifer L. Armentrout", genre = "Fantasia"),
           )
       }

       fun getBook(position: Int, books: MutableList<Book>?) : String {
           for(i in 0 until books!!.size) {
               if(i == position) {
                   return books!![i].name + " " + books!![i].author
               }
           }

           return ""
       }
   }


}