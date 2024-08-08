package com.example.searchapi.data


import com.google.gson.annotations.SerializedName

data class SearchData(
    @SerializedName("documents")
    val documents: List<Documents>,
    @SerializedName("meta")
    val meta: Meta
)