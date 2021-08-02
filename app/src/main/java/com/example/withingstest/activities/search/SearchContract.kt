package com.example.withingstest.activities.search

import android.widget.EditText
import com.example.withingstest.activities.BasePresenter
import com.example.withingstest.activities.BaseView
import com.example.withingstest.data.domain.ImageResult

interface SearchContract {
    interface Presenter : BasePresenter {
        fun getImage(searchInput: EditText)
    }

    interface View : BaseView<Presenter> {
        fun showImage(list : List<ImageResult>)
    }
}