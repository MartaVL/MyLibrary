package com.example.mylibrary.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mylibrary.R
import com.example.mylibrary.database.Book

class AdapterListBooks(mContext: Context, mLayoutResourceId: Int, books: MutableList<Book>):
    ArrayAdapter<Book>(mContext, mLayoutResourceId, books) {

    private val books: MutableList<Book> = ArrayList(books)
    private lateinit var name: TextView
    private lateinit var author: TextView

    override fun getCount(): Int {
        return books.size
    }

    override fun getItem(position: Int): Book {
        return books[position]
    }

    fun getIdItem(position: Int): Int {
        return books[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        convertView = LayoutInflater.from(context).inflate(R.layout.book_item_list, parent, false)

        name = convertView.findViewById(R.id.name)
        author = convertView.findViewById(R.id.author)

        name.text = books[position].name
        author.text = books[position].author

        return convertView!!
    }
}