package com.example.withingstest.activities.gallery

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.withingstest.R
import com.example.withingstest.data.domain.ImageResult




class GalleryActivity : AppCompatActivity(), GalleryContract.View {
    private lateinit var selectedImage : MutableList<ImageResult>
    private lateinit var recyclerView : RecyclerView
    private lateinit var galleryAdapter: GalleryAdapter
    override lateinit var presenter: GalleryContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        presenter = GalleryPresenter(this, applicationContext)
        selectedImage = this.intent.extras!!.getParcelableArrayList("images")!!
        recyclerView = findViewById(R.id.galleryContainer)
        presenter.start()
    }

    override fun showGallery() {
        galleryAdapter = GalleryAdapter(selectedImage)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@GalleryActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = galleryAdapter
        }
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollHorizontally(1)) {
                    Toast.makeText(this@GalleryActivity, "Last", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}