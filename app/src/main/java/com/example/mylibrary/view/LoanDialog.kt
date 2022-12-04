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
import com.example.mylibrary.databinding.LoanDialogBinding
import com.example.mylibrary.viewmodel.LibraryViewModel

class LoanDialog(private val idLoan: Int) : DialogFragment() {

    private var _binding: LoanDialogBinding? = null
    private val binding get() = _binding!!

    private val libraryViewModel: LibraryViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        _binding = LoanDialogBinding.inflate(LayoutInflater.from(context))
        val data = libraryViewModel.getLoan(idLoan)

        binding.name.text = data?.nameBook
        binding.destination.text = data?.contact
        binding.date.text = data?.date.toString()

        binding.checkReturn.isChecked = false

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setPositiveButton(R.string.positive_button, DialogInterface.OnClickListener { _, _ ->
                if(binding.checkReturn.isChecked) {
                    libraryViewModel.returnLoan(idLoan)
                }
                dialog?.cancel()
            })
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}