package com.example.mylibrary.data

data class Book(val name: String, val author: String, val genre: String) {
    var loan: Boolean = false
    var read: Boolean = false
    var score: Int = 0

}