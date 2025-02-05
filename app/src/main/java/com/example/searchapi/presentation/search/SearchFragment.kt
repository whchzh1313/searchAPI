package com.example.searchapi.presentation.search

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
import com.example.searchapi.data.Documents
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
        }

        recyclerView.layoutManager = GridLayoutManager(this@SearchFragment.context, 2)
        recyclerView.adapter = searchAdapter
        searchAdapter.itemClick = object : SearchListAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val clickItem = viewModel.items.value!!.get(position)


                Toast.makeText(this@SearchFragment.context, "clickItem.id = ${clickItem.uId}", Toast.LENGTH_SHORT).show()
                if (clickItem.like) false else true
                savePref(clickItem, clickItem.uId)
                // TODO 객체를 제이슨 형태로 변환해서 저장하고
                //  불러올때도 제이슨을 오브젝트로 변환하는 방법 찾기
            }
        }
    }

//    private fun saveData() {
//        val pref = requireActivity().getSharedPreferences("pref",0)
//        val edit = pref.edit()
//        // 수정 모드
//        edit.putString("name", "데이터가 넘어가긴한다")
//        edit.apply() // 저장완료
//    }
//
//    private fun loadData() {
//        val pref = requireActivity().getSharedPreferences("pref",0)
//        val loadDataTest = pref.getString("name","")
//        // 1번째 인자는 키, 2번째 인자는 데이터가 존재하지 않을경우의 값
//        Log.d("testNOW", "$loadDataTest")
//    }

    private fun savePref(value: Documents, key: String) {
        val sharedPreferences = requireActivity().getSharedPreferences("SharedPreference", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(value)
        editor.putString("SearchKey_$key", json)
        editor.apply()
        Log.d("debug", "Data saved")
    }
}