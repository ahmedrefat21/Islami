package com.bignerdranch.android.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.islami.adapters.AthkarDetailsAdapter
import com.bignerdranch.android.islami.constants.Constants
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONArray
import org.json.JSONObject

class AthkarDetailsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var titleTextView: TextView
    private lateinit var recycleView: RecyclerView
    private lateinit var previousImageButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_athkar_details)

        val index = intent.getIntExtra(Constants.ATHKAR_INDEX, 0)

        initComponent()

        titleTextView.text = resources.getStringArray(R.array.athkar)[index]

        val content = readContentFromFile(index)
        recycleView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recycleView.adapter = AthkarDetailsAdapter(content)
        previousImageButton.setOnClickListener(this)
    }

    private fun initComponent() {
        titleTextView = findViewById(R.id.athkar_details_activity_title)
        recycleView = findViewById(R.id.athkar_details_activity_recycler_view)
        previousImageButton = findViewById(R.id.athkar_details_activity_previous_image_button)
    }

    private fun readContentFromFile(index: Int): List<String> {
        val fileName = "athkar_assets/athkar.json"
        val jsonString = assets.open(fileName).bufferedReader().use { it.readText() }
        val j = JSONObject(jsonString)
        val x = j.optJSONArray(resources.getStringArray(R.array.athkar)[index])
        val l : ArrayList<Any> = ArrayList<Any>()
        if (x != null){
            for(i in 0 until x.length()){
                l.add(x.get(i))
            }
        }
        return l as List<String>

    }

    override fun onClick(view: View?) {
        val clickedViewId = view?.id
        if (clickedViewId == R.id.athkar_details_activity_previous_image_button) {
            finish()
        }

    }
}