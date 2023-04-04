package com.bignerdranch.android.islami.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.AthkarDetailsActivity
import com.bignerdranch.android.islami.HadeethDetailsActivity
import com.bignerdranch.android.islami.R
import com.bignerdranch.android.islami.adapters.AthkarAdapter
import com.bignerdranch.android.islami.constants.Constants


class AthkarFragment : Fragment() {
    private lateinit var recycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_athkar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = view.findViewById(R.id.athkar_fragment_recycler_view)
        recycleView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        val dataList = createDataList()


        val onAthkarClickListener = object : AthkarAdapter.OnAthkarClickListener {
            override fun onAthkarClick(position: Int) {
                val intent = Intent(context, AthkarDetailsActivity::class.java)
                intent.putExtra(Constants.ATHKAR_INDEX, position)
                startActivity(intent)
            }
        }
        recycleView.adapter = AthkarAdapter(dataList, onAthkarClickListener)
    }

    private fun createDataList(): List<String> {
        val list = mutableListOf<String>()
        for (i in 0 until resources.getStringArray(R.array.athkar).size) {
            list.add(resources.getStringArray(R.array.athkar)[i])
        }
        return list
    }


}