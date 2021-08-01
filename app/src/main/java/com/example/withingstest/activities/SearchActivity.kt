package com.example.withingstest.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Guideline
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.withingstest.R
import com.example.withingstest.data.RetrofitClient
import com.example.withingstest.data.domain.ImageResult
import kotlinx.coroutines.*
import retrofit2.awaitResponse

const val BASE_URL = "https://pixabay.com/"

class SearchActivity : AppCompatActivity() {
    private lateinit var searchButton: Button
    private lateinit var guideline: Guideline
    private lateinit var recyclerView : RecyclerView
    private lateinit var searchAdapter: SearchAdapter
    private var selectedImageResult: MutableList<ImageResult> = mutableListOf()
    private lateinit var searchInput : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchButton = findViewById(R.id.searchButton)
        guideline = findViewById(R.id.guideline2)
        recyclerView = findViewById(R.id.imageContainer)
        searchButton.setOnClickListener {
            recyclerView.visibility = View.VISIBLE
            getImage()
        }
        searchInput = findViewById(R.id.searchInput)
    }

    private fun getImage() {
        val q = if (searchInput.text.isNotEmpty()) searchInput.text.trim().toString() else ""
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getImage(q).awaitResponse()

                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d("getImage: ", data.toString())
                    withContext(Dispatchers.Main) {
                        showImage(data.hits)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { Log.e("getImage: ", it) }
                    Toast.makeText(
                        applicationContext,
                        "seems like something went wrong...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun showImage(list : List<ImageResult>) {
        searchAdapter = SearchAdapter(list) { itemView: View, image: ImageResult ->
            if (itemView.getTag(R.id.TAG_IMG_SELECTED) == false) {
                itemView.setTag(R.id.TAG_IMG_SELECTED, true)
                selectedImageResult.add(image)
            } else {
                itemView.setTag(R.id.TAG_IMG_SELECTED, false)
                selectedImageResult.remove(image)
            }
        }

        recyclerView.apply {
            layoutManager = GridLayoutManager(this@SearchActivity, 2)
            adapter = searchAdapter
        }
    }
}