package com.example.mylibrary.view

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter


class PageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0, 1-> {
                BookFragment(position)
            }
            2 -> {
                LoansFragment()
            }
            else -> {
                Fragment()
            }
        }
    }
}