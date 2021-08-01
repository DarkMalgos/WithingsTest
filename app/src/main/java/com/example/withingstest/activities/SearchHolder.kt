package com.example.withingstest.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.withingstest.R
import com.example.withingstest.data.domain.ImageResult

class SearchHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(R.layout.search_row, parent, false)) {

    fun bind(image: ImageResult, margin: Int?) {
    }
}