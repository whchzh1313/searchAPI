package com.example.searchapi.presentation.search

import android.app.Activity
import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchapi.data.Documents
import com.example.searchapi.data.SharedPreferences.deletePref
import com.example.searchapi.data.SharedPreferences.savePref
import com.example.searchapi.databinding.FragmentSearchBinding
import com.example.searchapi.presentation.adapter.SearchListAdapter
import com.google.gson.Gson

class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }

    private val viewModel: SearchViewModel by viewModels()
    private val binding by lazy { FragmentSearchBinding.inflate(layoutInflater) }
    private val searchAdapter by lazy { SearchListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, contaianer: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initViewModel() = with(viewModel) {
        items.observe(viewLifecycleOwner) { newList ->
            searchAdapter.submitList(newList)
        }
    }

    private fun initView() = with(binding) {
        searchBtSearch.setOnClickListener{
            viewModel.communicateNetWork(binding.searchEtSearch.text.toString()?: "")
            hideKeyboard(binding.root)
        }

        recyclerView.layoutManager = GridLayoutManager(this@SearchFragment.context, 2)
        recyclerView.adapter = searchAdapter
        searchAdapter.itemClick = object : SearchListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val clickItem = viewModel.items.value!!.get(position)
                Toast.makeText(this@SearchFragment.context, "이미지가 북마크에 추가되었습니다.", Toast.LENGTH_SHORT).show()
                if (clickItem.like) {
                    clickItem.like = false
                    deletePref(requireContext(), clickItem.uId)
                } else {
                    clickItem.like = true
                    savePref(requireContext(),clickItem, clickItem.uId)
                }
            }
        }
    }
    private fun hideKeyboard(view: View) {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}