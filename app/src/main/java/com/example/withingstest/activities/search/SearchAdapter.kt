package com.example.withingstest.activities.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.withingstest.R
import com.example.withingstest.data.domain.ImageResult

class SearchAdapter(private var list: List<ImageResult>, private val onItemClicked: (SearchHolder, ImageResult) -> Unit) :
    RecyclerView.Adapter<SearchHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SearchHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: SearchHolder, position: Int) {
        val image: ImageResult = list[position]
        if (position != itemCount)
            holder.bind(image, 16)
        else
            holder.bind(image, null)

        holder.itemView.setTag(R.id.TAG_IMG_SELECTED, false)
        holder.itemView.setOnClickListener { onItemClicked(holder, image) }
    }

    fun updateList(list: List<ImageResult>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}