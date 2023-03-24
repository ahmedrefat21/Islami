package com.bignerdranch.android.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.R

class HadeethAdapter(
    private val dataList: List<String>,
    private val onHadeethClickListener: OnHadeethClickListener
) : RecyclerView.Adapter<HadeethAdapter.HadeethViewHolder>() {

    interface OnHadeethClickListener {
        fun onHadeethClick(position: Int)
    }

    class HadeethViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hadeethNumberTextView: TextView =
            itemView.findViewById(R.id.hadeeth_layout_hadeeth_number_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HadeethViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.hadeeth_layout, parent, false)

        return HadeethViewHolder(view)
    }

    override fun onBindViewHolder(holder: HadeethViewHolder, position: Int) {
        val hadeethNumber: String = dataList[position]

        holder.hadeethNumberTextView.text = hadeethNumber

        holder.hadeethNumberTextView.setOnClickListener {
            onHadeethClickListener.onHadeethClick(position)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

