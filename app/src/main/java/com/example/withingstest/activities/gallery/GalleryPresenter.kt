package com.example.withingstest.activities.gallery

import android.content.Context

class GalleryPresenter(view: GalleryContract.View, val context: Context) : GalleryContract.Presenter {
    var view: GalleryContract.View? = view

    override fun start() {
        view!!.showGallery()
    }

    override fun stop() {
        view = null
    }
}