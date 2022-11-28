package com.example.mylibrary.data

import android.text.SpannableStringBuilder
import androidx.core.text.bold
import androidx.core.text.italic

data class Book(val name: String, val author: String, val genre: String) {
    var loan: Boolean = false
    var read: Boolean = false
    var score: Int = 0

    override fun toString() : String {
        var str = SpannableStringBuilder()
            .bold { append(name) }
            .append("\n\t\t")
            .italic { append(author) }

        return str.toString()
    }
}