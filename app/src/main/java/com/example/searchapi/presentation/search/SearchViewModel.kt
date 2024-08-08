package com.example.searchapi.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.searchapi.data.DocumentModel
import com.example.searchapi.data.toModel
import com.example.searchapi.network.NetWorkClient
import kotlinx.coroutines.launch


class SearchViewModel : ViewModel() {
//    var items = mutableListOf<Documents>()
    private val _items = MutableLiveData<List<DocumentModel>>()
    val items: LiveData<List<DocumentModel>>
        get() = _items

    fun communicateNetWork(param: String){
        viewModelScope.launch {
            val responseData = NetWorkClient.searchNetWork.getSearchList(param)
            val documentModel = toModel(responseData.documents)
            _items.value = documentModel
        }
    }


}