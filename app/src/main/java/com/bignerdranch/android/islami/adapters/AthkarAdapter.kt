package com.bignerdranch.android.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.R

class AthkarAdapter(
    val datalist: List<String>,
    val onAthkarClickListener: AthkarAdapter.OnAthkarClickListener
): RecyclerView.Adapter<AthkarAdapter.AthkarViewHolder>(){

    interface OnAthkarClickListener {
        fun onAthkarClick(position: Int)
    }

    class AthkarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val athkarTextView: TextView =
            itemView.findViewById(R.id.athkar_layout_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthkarAdapter.AthkarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.athkar_layout, parent, false)

        return AthkarAdapter.AthkarViewHolder(view)
    }

    override fun onBindViewHolder(holder: AthkarAdapter.AthkarViewHolder, position: Int) {
        val text: String = datalist[position]

        holder.athkarTextView.text = text

        holder.athkarTextView.setOnClickListener {
            onAthkarClickListener.onAthkarClick(position)
        }

    }

    override fun getItemCount(): Int {
        return datalist.size
    }

}
