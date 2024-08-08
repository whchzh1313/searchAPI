package com.example.searchapi.data


import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("documents")
    val documents: MutableList<Documents>,
    @SerializedName("meta")
    val meta: Meta
)