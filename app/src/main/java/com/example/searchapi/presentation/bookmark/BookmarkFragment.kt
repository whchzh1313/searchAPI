package com.example.searchapi.presentation.bookmark

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.searchapi.data.SharedPreferences.deletePref
import com.example.searchapi.data.SharedPreferences.getAll
import com.example.searchapi.databinding.FragmentBookmarkBinding
import com.example.searchapi.presentation.adapter.SearchListAdapter

class BookmarkFragment : Fragment() {

    companion object {
        fun newInstance() = BookmarkFragment()
    }

    private val viewModel: BookmarkViewModel by viewModels()
    private val binding by lazy { FragmentBookmarkBinding.inflate(layoutInflater) }
    private val recyclerViewAdapter by lazy { SearchListAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        initView()
        initViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val jsonString = getAll(requireContext())
        viewModel.fetchDocuments(jsonString.toString())
    }
    private fun initViewModel() = with(viewModel) {
        val jsonString = getAll(requireContext())
        fetchDocuments(jsonString.toString())
        documents.observe(viewLifecycleOwner) { newList ->
            recyclerViewAdapter.submitList(newList)
        }
    }

    private fun initView() = with(binding) {
        bookmarkRecyclerView.layoutManager = GridLayoutManager(this@BookmarkFragment.context, 2)
        bookmarkRecyclerView.adapter = recyclerViewAdapter

        recyclerViewAdapter.itemClick = object : SearchListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val clickItem = viewModel.documents.value?.get(position)
                Toast.makeText(this@BookmarkFragment.context, "이미지가 삭제되었습니다.", Toast.LENGTH_SHORT).show()
                deletePref(requireContext(), clickItem!!.uId)
                val jsonString = getAll(requireContext())
                viewModel.fetchDocuments(jsonString.toString())
            }
        }
    }
}