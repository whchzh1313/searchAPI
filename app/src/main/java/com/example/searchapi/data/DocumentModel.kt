package com.example.searchapi.data

import com.google.gson.Gson
import java.util.UUID

data class DocumentModel(
    val uId : String = UUID.randomUUID().toString(),
    var like : Boolean = false,
    val collection: String,
    val datetime: String,
    val displaySitename: String,
    val docUrl: String,
    val height: Int,
    val imageUrl: String,
    val thumbnailUrl: String,
    val width: Int
)

fun toModel(documentReponse : List<Documents>) : List<DocumentModel> = with(documentReponse) {
    return map { document ->
        DocumentModel(
            collection = document.collection,
            datetime = document.datetime,
            displaySitename = document.displaySitename,
            docUrl = document.docUrl,
            height = document.height,
            imageUrl = document.imageUrl,
            thumbnailUrl = document.thumbnailUrl,
            width = document.width,
        )
    }
}