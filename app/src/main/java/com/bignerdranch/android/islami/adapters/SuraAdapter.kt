package com.bignerdranch.android.islami.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.R
import com.bignerdranch.android.islami.models.SuraDataModel

class SuraAdapter(private val dataList: List<SuraDataModel>, private val onSuraClickListener:OnSuraClickListener):RecyclerView.Adapter<SuraAdapter.SuraViewHolder>() {

    interface OnSuraClickListener{
        fun onSuraClick(position:Int)
    }

    class SuraViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val suraNameTextView: TextView = itemView.findViewById(R.id.sura_layout_sura_name_tv)
        val ayhaatNumberTextView: TextView = itemView.findViewById(R.id.sura_layout_ayah_numbers_tv)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sura_layout,parent,false)

        return SuraViewHolder(view)
    }

    override fun onBindViewHolder(holder: SuraViewHolder, position: Int) {
        val suraData: SuraDataModel = dataList[position]

        holder.suraNameTextView.text = suraData.suraName
        holder.ayhaatNumberTextView.text = suraData.ayhaatNumber.toString()

        holder.suraNameTextView.setOnClickListener {
            onSuraClickListener.onSuraClick(position)
        }

        holder.ayhaatNumberTextView.setOnClickListener {
            onSuraClickListener.onSuraClick(position)
        }

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

