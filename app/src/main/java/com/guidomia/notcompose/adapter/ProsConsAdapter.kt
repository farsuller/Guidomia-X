package com.guidomia.notcompose.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.guidomia.notcompose.R

class ProsConsAdapter(private val items: List<Any>) : RecyclerView.Adapter<ProsConsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProsConsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.pros_cons_items, parent, false)
        return ProsConsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProsConsViewHolder, position: Int) {
        holder.prosConsTextView.text = items[position].toString()
    }

    override fun getItemCount() = items.size
}

class ProsConsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val prosConsTextView: TextView = itemView.findViewById(R.id.prosConsText)
}