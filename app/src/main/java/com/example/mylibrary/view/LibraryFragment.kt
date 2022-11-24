package com.example.mylibrary.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.mylibrary.data.Book
import com.example.mylibrary.databinding.LibraryFragmentBinding
import com.example.mylibrary.viewmodel.LibraryViewModel

class LibraryFragment() : Fragment() {

    private var adapter: ArrayAdapter<Book>? = null
    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!
    private val libraryViewModel: LibraryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        libraryViewModel.libraryModel.observe(viewLifecycleOwner, Observer {
            adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, it)
            binding.listBooks.adapter = adapter
        })

        binding.listBooks.setOnItemClickListener {parent, view, position, id ->
            Toast.makeText(requireContext(), libraryViewModel.getData(position),   Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        libraryViewModel.loadData()
    }
}