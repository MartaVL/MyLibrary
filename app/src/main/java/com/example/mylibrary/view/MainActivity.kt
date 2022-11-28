package com.example.mylibrary.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mylibrary.R
import com.example.mylibrary.databinding.ActivityMainBinding
import com.example.mylibrary.viewmodel.LibraryViewModel
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 3

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val libraryViewModel: LibraryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PageAdapter(this)
        binding.pager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.pager) {tab, position->
            when(position){
                0 -> {
                    tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.library, null)
                }
                1 -> {
                    tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.tbr, null)
                }
                2 -> {
                    tab.icon = ResourcesCompat.getDrawable(resources, R.drawable.loans, null)
                }
            }
        }.attach()
    }
}