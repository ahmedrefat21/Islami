package com.bignerdranch.android.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.R

class AthkarDetailsAdapter(
    val datalist: List<String>,
): RecyclerView.Adapter<AthkarDetailsAdapter.AthkarDetailsViewHolder>() {

    class AthkarDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val athkarTextView: TextView =
            itemView.findViewById(R.id.athkar_details_layout_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AthkarDetailsAdapter.AthkarDetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.athkar_details_layout, parent, false)

        return AthkarDetailsAdapter.AthkarDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AthkarDetailsAdapter.AthkarDetailsViewHolder, position: Int) {
        val text: String = datalist[position]
        holder.athkarTextView.text = text

    }

    override fun getItemCount(): Int {
        return datalist.size
    }
}