package com.example.searchapi.presentation.bookmark

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.searchapi.data.DocumentModel
import com.google.gson.Gson

class BookmarkViewModel : ViewModel() {

    val documents: MutableLiveData<List<DocumentModel>> = MutableLiveData()
    fun fetchDocuments(jsonString: String) {
        val gson = Gson()
        val jsonObject = gson.fromJson(jsonString, Map::class.java)
        val items = mutableListOf<DocumentModel>()
        for (i in jsonObject.values) {
            val documentModel = gson.fromJson(i.toString(), DocumentModel::class.java)
            items.add(documentModel)
        }
        documents.value = items
    }

    fun removeSelectItem (uId: String) {
        val removeList = documents.value?.toMutableList() ?: return
        removeList.removeAll { it.uId == uId}
        documents.value = removeList
    }

}