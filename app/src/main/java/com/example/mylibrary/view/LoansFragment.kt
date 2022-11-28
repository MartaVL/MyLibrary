package com.example.mylibrary.view

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mylibrary.data.Book
import com.example.mylibrary.databinding.LoansFragmentBinding
import com.example.mylibrary.viewmodel.LibraryViewModel

class LoansFragment() : Fragment() {

    private var adapter: ArrayAdapter<Book>? = null
    private var _binding: LoansFragmentBinding? = null
    private val binding get() = _binding!!

    private val libraryViewModel: LibraryViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoansFragmentBinding.inflate(inflater, container, false)
        libraryViewModel.loansBooks.observe(viewLifecycleOwner, Observer {
            adapter = ArrayAdapter(requireContext(), R.layout.simple_list_item_1, it)
            binding.listLoans.adapter = adapter
        })

        binding.listLoans.setOnItemClickListener { _, _, position, _ ->
            val dialog = BookDialog(position)
            dialog.show(childFragmentManager, "Book")
        }

        return binding.root
    }
}