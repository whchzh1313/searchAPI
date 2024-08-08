package com.example.searchapi.presentation.search

import com.example.searchapi.data.Documents
import java.util.Date
import java.util.UUID

data class DocumentModel(
    val uId : String = UUID.randomUUID().toString(),
    val like : Boolean = false,
    val collection: String,
    val datetime: Date,
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
            width = document.width
        )
    }
}
