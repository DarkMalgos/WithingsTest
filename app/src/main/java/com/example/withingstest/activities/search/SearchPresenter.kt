package com.example.withingstest.activities.search

import android.content.Context
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.withingstest.data.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class SearchPresenter(view: SearchContract.View, val context: Context) : SearchContract.Presenter {
    var view: SearchContract.View? = view

    override fun start() {
    }

    override fun stop() {
        view = null
    }

    override fun getImage(searchInput: EditText) {
        GlobalScope.launch(Dispatchers.Main) {
            //todo start loader
        }

        val q = if (searchInput.text.isNotEmpty()) searchInput.text.trim().toString() else ""
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitClient.apiService.getImage(q).awaitResponse()

                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d("getImage: ", data.toString())
                    withContext(Dispatchers.Main) {
                        view!!.showImage(data.hits)
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    e.message?.let { Log.e("getImage: ", it) }
                    Toast.makeText(
                        context,
                        "seems like something went wrong...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}