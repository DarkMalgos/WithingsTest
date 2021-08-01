package com.example.withingstest.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.withingstest.R
import com.example.withingstest.data.domain.ImageResult
import com.squareup.picasso.Picasso


class SearchHolder(inflater: LayoutInflater, private val parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.search_row, parent, false)) {
    var mImage: ImageView = itemView.findViewById(R.id.imageView)
    var isSelected: Boolean = false

    fun bind(image: ImageResult, margin: Int?) {
        Picasso.with(parent.context)
            .load(image.largeImageURL)               //optional
            .into(mImage);
    }
}