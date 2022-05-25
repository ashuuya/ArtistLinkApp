package com.example.artistlink

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlink.Adapter.InfoAdapter
import com.example.artistlink.Common.Common
import com.example.artistlink.Interface.RetrofitServices
import com.example.artistlink.Model.InfoModel
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: InfoAdapter
    lateinit var searchText: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
        searchText = findViewById(R.id.searchView)

        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerLinkList).layoutManager = layoutManager
        searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
                searchText.isIconified = true
                getAllLinks(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
//                findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE
//                getAllLinks(newText)
                return false
            }
        })
    }

    private fun getAllLinks(searchText: String) {
        mService.getLinkList(searchText).enqueue(object : Callback<InfoModel> {
            override fun onFailure(call: Call<InfoModel>, t: Throwable) {
                findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
                Log.d("tag", t.message!!)
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<InfoModel>, response: Response<InfoModel?>) {
                findViewById<ProgressBar>(R.id.progressBar).visibility = View.INVISIBLE
                var infoModel = response.body()
                if (infoModel != null) {
                    adapter = InfoAdapter(baseContext, infoModel.platforms)
                    adapter.notifyDataSetChanged()

                    findViewById<RecyclerView>(R.id.recyclerLinkList).adapter = adapter
                    Picasso.get().load(infoModel.image).into(findViewById<ImageView>(R.id.artistImage))
                    findViewById<TextView>(R.id.artistNameText).setText(infoModel.name)
                }else
                {
                    findViewById<TextView>(R.id.artistNameText).setText("Не найдено!")
                }
            }
        })
    }
}