package com.example.withingstest.activities.gallery

import com.example.withingstest.activities.BasePresenter
import com.example.withingstest.activities.BaseView

interface GalleryContract {
    interface Presenter : BasePresenter {
    }

    interface View : BaseView<Presenter> {
        fun showGallery()
    }
}