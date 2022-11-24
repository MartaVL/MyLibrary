package com.example.mylibrary.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mylibrary.view.LibraryFragment
import com.example.mylibrary.view.LoansFragment
import com.example.mylibrary.view.TbrFragment


class PageAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> {
                LibraryFragment()
            }
            1 -> {
                TbrFragment()
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