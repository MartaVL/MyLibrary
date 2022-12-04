package com.example.mylibrary.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.mylibrary.R
import com.example.mylibrary.database.Loan

class AdapterListLoans(mContext: Context, mLayoutResourceId: Int, loans: MutableList<Loan>):
    ArrayAdapter<Loan>(mContext, mLayoutResourceId, loans) {

    private val loans: MutableList<Loan> = ArrayList(loans)
    private lateinit var name: TextView
    private lateinit var contact: TextView
    private lateinit var date: TextView

    override fun getCount(): Int {
        return loans.size
    }

    override fun getItem(position: Int): Loan {
        return loans[position]
    }

    fun getIdItem(position: Int): Int {
        return loans[position].id
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView

        convertView = LayoutInflater.from(context).inflate(R.layout.loan_item_list, parent, false)

        name = convertView.findViewById(R.id.name)
        contact = convertView.findViewById(R.id.contact)
        date = convertView.findViewById(R.id.score)

        name.text = loans[position].nameBook
        contact.text = loans[position].contact
        date.text = loans[position].date.toString()

        return convertView!!
    }
}