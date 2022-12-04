package com.example.mylibrary.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mylibrary.databinding.BookFragmentBinding
import com.example.mylibrary.viewmodel.LibraryViewModel

class BookFragment(private val tab: Int) : Fragment() {

    private var adapter: AdapterListBooks? = null
    private var _binding: BookFragmentBinding? = null
    private val binding get() = _binding!!

    private val libraryViewModel: LibraryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BookFragmentBinding.inflate(inflater, container, false)
        if(tab == 0) {
            libraryViewModel.allBooks.observe(viewLifecycleOwner, Observer {
                adapter = AdapterListBooks(requireContext(), R.layout.simple_list_item_1, it)
                binding.listBooks.adapter = adapter
            })
        } else {
            libraryViewModel.noReadBooks.observe(viewLifecycleOwner, Observer {
                adapter = AdapterListBooks(requireContext(), R.layout.simple_list_item_1, it)
                binding.listBooks.adapter = adapter
            })
        }


        binding.listBooks.setOnItemClickListener { _, _, position, _ ->
            adapter?.let { BookDialog(it.getIdItem(position)) }
                ?.show(childFragmentManager, "Book")
        }

        return binding.root
    }
}