package com.bignerdranch.android.islami.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.QuranDetailsActivity
import com.bignerdranch.android.islami.R
import com.bignerdranch.android.islami.adapters.SuraAdapter
import com.bignerdranch.android.islami.constants.Constants
import com.bignerdranch.android.islami.models.SuraDataModel
import java.util.*

class QuranFragment : Fragment() {

    private lateinit var recycleView: RecyclerView

    private lateinit var surasNames: Array<String>
    private lateinit var ayhaatNumbers: Array<String>

    val suraDataList: MutableList<SuraDataModel> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quran, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        surasNames = resources.getStringArray(R.array.sura_name)
        ayhaatNumbers = resources.getStringArray(R.array.ayhaat_numbers)
        recycleView = view.findViewById(R.id.quran_fragment_sura_recycler_view)
        recycleView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        createDataList()

        val onSuraClickListener = object : SuraAdapter.OnSuraClickListener {
            override fun onSuraClick(position: Int) {
                val intent = Intent(context, QuranDetailsActivity::class.java)
                intent.putExtra(Constants.SURA_NAME, surasNames[position])
                intent.putExtra(Constants.SURA_NUMBER, position)
                startActivity(intent)
            }
        }

        recycleView.adapter = SuraAdapter(suraDataList, onSuraClickListener)
    }

    private fun createDataList() {
        for (i in 0 until (surasNames.size)) {
            suraDataList.add(SuraDataModel(surasNames[i], ayhaatNumbers[i]))
        }
    }


}