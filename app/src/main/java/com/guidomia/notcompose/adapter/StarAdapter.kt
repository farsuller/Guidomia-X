package com.guidomia.notcompose.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.guidomia.notcompose.R

class StarAdapter(private val items: Int) : RecyclerView.Adapter<StarViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StarViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.star_item, parent, false)
        return StarViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: StarViewHolder, position: Int) {
        holder.prosConsTextView.setImageResource(R.drawable.star_icon)
    }

    override fun getItemCount() = items
}

class StarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val prosConsTextView: ImageView = itemView.findViewById(R.id.star_item)
}