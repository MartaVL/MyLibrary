package com.example.mylibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

private const val NUM_PAGES = 3

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        viewPager = findViewById(R.id.pager)
        tabLayout = findViewById(R.id.tabLayout)

        val adapter = PageAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) {tab, position->
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