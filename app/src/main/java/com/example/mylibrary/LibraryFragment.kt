package com.example.mylibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment

class LibraryFragment() : Fragment() {

    val listOfBooks = arrayOf<Book>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.library_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val libro1: Book = Book("Una corte de rosas y espinas", "Sarah J. Mass", "Fantasia")
        listOfBooks.toMutableList().add(libro1)
        val libro2: Book = Book("De Sangre y cenizas", "Jennifer L. Armentrout", "Fantasia")
        listOfBooks.toMutableList().add(libro2)

        var listBook: ListView = view.findViewById(R.id.listBooks)
        var position: Int = 0


    }

}