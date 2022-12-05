package com.example.mylibrary.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
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
        binding.score.text = Editable.Factory.getInstance().newEditable(if(data?.score != 0 ) data?.score.toString() else getString(R.string.score))

        binding.checkLoan.isChecked = data?.loan == true
        binding.checkRead.isChecked = data?.read == true
        binding.checkRead.isEnabled = !binding.checkRead.isChecked

        binding.score.visibility = if(binding.checkRead.isChecked) View.VISIBLE else View.GONE
        binding.to.visibility = if(binding.checkLoan.isChecked) View.VISIBLE else View.GONE

        binding.checkLoan.setOnClickListener { _ ->
            if(binding.checkLoan.isChecked) binding.to.visibility = View.VISIBLE else binding.to.visibility = View.GONE
        }

        binding.checkRead.setOnClickListener { _ ->
            if(binding.checkRead.isChecked) binding.score.visibility = View.VISIBLE else binding.score.visibility = View.GONE
        }

        return AlertDialog.Builder(requireActivity())
            .setView(binding.root)
            .setPositiveButton(getString(R.string.positive_button)) { _, _ ->
                val score =
                    if (binding.score.text.toString() == getString(R.string.score)) 0 else binding.score.text.toString().toInt()
                val lentTo =
                    if (binding.checkLoan.isChecked) if (binding.to.text.toString() == getString(R.string.to)) "" else binding.to.text.toString() else ""

                if ((binding.checkRead.isChecked && (score < 1 || score > 5)) || binding.checkLoan.isChecked && lentTo == "") {
                    if (binding.checkRead.isChecked) {
                        binding.score.error = getString(R.string.errorScore)
                    } else if (binding.checkLoan.isChecked) {
                        binding.to.error = getString(R.string.errorTo)
                    }
                } else {
                    libraryViewModel.saveBook(idBook, binding.checkRead.isChecked, binding.checkLoan.isChecked, score, lentTo)
                }
            }
            .create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

