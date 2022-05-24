package com.example.artistlink

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mService = Common.retrofitService
        layoutManager = LinearLayoutManager(this)
        findViewById<RecyclerView>(R.id.recyclerLinkList).layoutManager = layoutManager
        getAllLinks()
    }

    private fun getAllLinks() {
        mService.getLinkList("Hollywood Undead").enqueue(object : Callback<InfoModel> {
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
                    Picasso.get().load(infoModel.image).into(findViewById<ImageView>(R.id.artist_image))
                    findViewById<TextView>(R.id.artistNameText).setText(infoModel.name)
                }else
                {
                    findViewById<TextView>(R.id.artistNameText).setText("Не найдено!")
                }
            }
        })
    }
}