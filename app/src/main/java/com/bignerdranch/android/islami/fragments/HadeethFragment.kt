package com.bignerdranch.android.islami.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.HadeethDetailsActivity
import com.bignerdranch.android.islami.R
import com.bignerdranch.android.islami.adapters.HadeethAdapter
import com.bignerdranch.android.islami.constants.Constants

class HadeethFragment : Fragment() {
    private lateinit var recycleView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hadeeth, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView = view.findViewById(R.id.hadeeth_fragment_recycler_view)
        recycleView.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        val hadeethDataList = createHadeethDataList()

        val onHadeethClickListener = object : HadeethAdapter.OnHadeethClickListener {
            override fun onHadeethClick(position: Int) {
                val intent = Intent(context, HadeethDetailsActivity::class.java)
                intent.putExtra(Constants.HADEETH_NUMBER, position)
                startActivity(intent)
            }
        }
        recycleView.adapter = HadeethAdapter(hadeethDataList, onHadeethClickListener)
    }

    private fun createHadeethDataList(): List<String> {
        val hadeethList = mutableListOf<String>()
        for (i in 1..50) {
            hadeethList.add(resources.getString(R.string.hadeeth_number) +" "+ resources.getStringArray(R.array.numbers)[i])
        }
        return hadeethList
    }

}

