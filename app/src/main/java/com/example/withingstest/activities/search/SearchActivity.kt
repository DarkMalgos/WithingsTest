package com.example.withingstest.activities.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Guideline
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.withingstest.R
import com.example.withingstest.activities.gallery.GalleryActivity
import com.example.withingstest.data.domain.ImageResult
import kotlinx.coroutines.*

const val BASE_URL = "https://pixabay.com/"

class SearchActivity() : AppCompatActivity(), SearchContract.View {
    private lateinit var searchButton: Button
    private lateinit var validateButton: Button
    private lateinit var guideline: Guideline
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private var selectedImageResult: MutableList<ImageResult> = mutableListOf()
    private lateinit var searchInput : EditText
    override lateinit var presenter: SearchContract.Presenter
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = SearchPresenter(this, applicationContext)
        searchButton = findViewById(R.id.searchButton)
        guideline = findViewById(R.id.guideline2)
        recyclerView = findViewById(R.id.imageContainer)
        searchInput = findViewById(R.id.searchInput)
        searchButton.setOnClickListener {
            recyclerView.visibility = View.VISIBLE
            presenter.getImage(searchInput)
        }
        validateButton = findViewById(R.id.validateButton)
        validateButton.setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            intent.putParcelableArrayListExtra("images", ArrayList(selectedImageResult))
            startActivity(intent)
        }
        progressBar = findViewById(R.id.progressBar)
    }

    override fun showImage(list : List<ImageResult>) {
        progressBar.visibility = View.GONE
        searchAdapter = SearchAdapter(list) { holder: SearchHolder, image: ImageResult ->
            val itemView = holder.itemView
            if (itemView.getTag(R.id.TAG_IMG_SELECTED) == false) {
                itemView.setTag(R.id.TAG_IMG_SELECTED, true)
                holder.mIndicator.setImageResource(android.R.drawable.presence_online)
                selectedImageResult.add(image)
            } else {
                itemView.setTag(R.id.TAG_IMG_SELECTED, false)
                holder.mIndicator.setImageResource(android.R.drawable.presence_invisible)
                selectedImageResult.remove(image)
            }
            checkSelectedItem()
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
            adapter = searchAdapter
        }
    }

    override fun startLoader() {
        progressBar.visibility = View.VISIBLE
    }

    private fun checkSelectedItem() {
        if (selectedImageResult.size >= 2) {
            validateButton.visibility = View.VISIBLE
        } else {
            validateButton.visibility = View.GONE
        }
    }
}
