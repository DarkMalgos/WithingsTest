package com.example.withingstest.activities.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.withingstest.data.domain.ImageResult

class GalleryAdapter(private var list: List<ImageResult>) :
    RecyclerView.Adapter<GalleryHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val inflater = LayoutInflater.from(parent.context)
        return GalleryHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        val image: ImageResult = list[position]
        holder.bind(image, 30)
    }

    fun updateList(list: List<ImageResult>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size
}