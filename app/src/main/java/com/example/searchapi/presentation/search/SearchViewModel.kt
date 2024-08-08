package com.example.searchapi.presentation.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchapi.data.Documents
import com.example.searchapi.network.NetWorkClient
import com.example.searchapi.presentation.adapter.SearchListAdapter
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class SearchViewModel : ViewModel() {
//    var items = mutableListOf<Documents>()
    private val _items = MutableLiveData<MutableList<Documents>>()
    val items: LiveData<MutableList<Documents>>
        get() = _items

    fun communicateNetWork(param: String){
        viewModelScope.launch {
            val responseData = NetWorkClient.searchNetWork.getSearchList(param)
            _items.value = responseData.documents
        }
    }


}