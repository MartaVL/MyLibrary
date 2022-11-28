package com.example.mylibrary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mylibrary.data.Book
import com.example.mylibrary.databinding.LibraryFragmentBinding
import com.example.mylibrary.viewmodel.LibraryViewModel

class LibraryFragment(private val type: String) : Fragment() {

    private var adapter: ArrayAdapter<Book>? = null
    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!

    private val libraryViewModel: LibraryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        if(type == "ALL") {
            libraryViewModel.allBooks.observe(viewLifecycleOwner, Observer {
                adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
                binding.listBooks.adapter = adapter
            })
        } else {
            libraryViewModel.noReadBooks.observe(viewLifecycleOwner, Observer {
                adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
                binding.listBooks.adapter = adapter
            })
        }

        binding.listBooks.setOnItemClickListener { _, _, position, _ ->
            val dialog = BookDialog(position)
            dialog.show(childFragmentManager, "Book")
        }

        return binding.root
    }
}