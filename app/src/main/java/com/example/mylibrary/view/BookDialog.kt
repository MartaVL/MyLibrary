package com.example.mylibrary.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.mylibrary.R
import com.example.mylibrary.databinding.BookDialogBinding
import com.example.mylibrary.viewmodel.LibraryViewModel

class BookDialog(private val idBook: Int) : DialogFragment() {

    private var _binding: BookDialogBinding? = null
    private val binding get() = _binding!!

    private val libraryViewModel: LibraryViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        _binding = BookDialogBinding.inflate(LayoutInflater.from(context))
        val data = libraryViewModel.getBook(idBook)

        binding.name.text = data?.name
        binding.author.text = data?.author

        binding.genre.text = getString(R.string.genre).plus(" ").plus(data?.genre)
        binding.score.text = getString(R.string.score).plus(" ").plus(data?.score).plus("/5")

        binding.checkLoan.isChecked = data?.loan == true
        binding.checkRead.isChecked = data?.read == true

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setPositiveButton(R.string.positive_button, DialogInterface.OnClickListener { _, _ ->
                val scoreIntArray = binding.score.text.toString().split(" ")
                val scoreInt = ((scoreIntArray[scoreIntArray.size - 1]).split("/")[0]).toInt()
                libraryViewModel.saveBook(idBook, binding.checkRead.isChecked, binding.checkLoan.isChecked, scoreInt)
                dialog?.cancel()
            })
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}