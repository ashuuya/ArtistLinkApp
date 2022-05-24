package com.example.artistlink.Adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.artistlink.Model.Platform
import com.example.artistlink.R

class InfoAdapter(private val context: Context, private val linkList: MutableList<Platform>):RecyclerView.Adapter<InfoAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val source_name: TextView = itemView.findViewById(R.id.sr_text)

        fun bind(listItem: Platform) {
            itemView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(listItem.url))
                it.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = linkList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val listItem = linkList[position]
        holder.bind(listItem)
        holder.source_name.text = linkList[position].name
    }

}