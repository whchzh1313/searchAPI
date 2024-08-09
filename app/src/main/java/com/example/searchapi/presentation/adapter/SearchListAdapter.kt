package com.example.searchapi.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.searchapi.R
import com.example.searchapi.data.DocumentModel
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

class SearchListAdapter : ListAdapter<DocumentModel, SearchListAdapter.SearchViewHolder>(object : DiffUtil.ItemCallback<DocumentModel>() {
    override fun areItemsTheSame(oldItem: DocumentModel, newItem: DocumentModel): Boolean {
        return oldItem.thumbnailUrl == newItem.thumbnailUrl && oldItem.imageUrl == newItem.imageUrl
    }

    override fun areContentsTheSame(oldItem: DocumentModel, newItem: DocumentModel): Boolean {
        return oldItem == newItem
    }
}) {
    interface ItemClick {
        fun onClick(view: View, position: Int)
    }
    interface ItemLongClick {
        fun onLongClick(view: View, position: Int)
    }

    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        Glide.with(holder.listThumbnail.context)
            .load(getItem(position).thumbnailUrl)
            .into(holder.listThumbnail)
        holder.listTitle.text = getItem(position).displaySitename

        val offsetDateTime = OffsetDateTime.parse(getItem(position).datetime)
        val dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm")
        val date = offsetDateTime.format(dateFormat)
        holder.listDate.text = date

        if (getItem(position).like) {
            holder.listLike.setImageResource(R.drawable.heart_on)
        } else {
            holder.listLike.setImageResource(R.drawable.heart_off)
        }

        holder.itemView.setOnClickListener {
            itemClick?.onClick(it, position)
            if (getItem(position).like) {
                holder.listLike.setImageResource(R.drawable.heart_on)
            } else {
                holder.listLike.setImageResource(R.drawable.heart_off)
            }
        }
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val listThumbnail: ImageView = view.findViewById(R.id.list_item_thumbnail)
        val listTitle: TextView = view.findViewById(R.id.list_item_title)
        val listDate: TextView = view.findViewById(R.id.list_item_datetime)
        val listLike: ImageView = view.findViewById(R.id.list_item_like)
    }


}