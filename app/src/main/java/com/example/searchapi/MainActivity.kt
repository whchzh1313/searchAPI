package com.example.searchapi

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.searchapi.databinding.ActivityMainBinding
import com.example.searchapi.presentation.adapter.ViewPager2Adapter
import com.example.searchapi.presentation.search.SearchViewModel
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken


class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val viewPagerAdapter = ViewPager2Adapter(this)
        binding.fragmentViewPager.adapter = viewPagerAdapter

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.search_fragment_move -> {
                    binding.fragmentViewPager.currentItem = 0
                    true
                }
                R.id.bookmark_fragment_move -> {
                    binding.fragmentViewPager.currentItem = 1
                    true
                }
                else -> false
            }
        }
    }




}