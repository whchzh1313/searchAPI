package com.example.searchapi.presentation.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.searchapi.presentation.bookmark.BookmarkFragment
import com.example.searchapi.presentation.search.SearchFragment

class ViewPager2Adapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 2 // 페이지 수

    override fun createFragment(position: Int) = when (position) {
        0 -> SearchFragment()
        1 -> BookmarkFragment()
        else -> throw IllegalStateException("Invalid position: $position")
    }
}