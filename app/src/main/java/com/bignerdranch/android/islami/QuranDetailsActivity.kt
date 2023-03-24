package com.bignerdranch.android.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.bignerdranch.android.islami.constants.Constants

class QuranDetailsActivity : AppCompatActivity(), View.OnClickListener {


    private lateinit var suraDetailsTextView: TextView
    private lateinit var suraNameTextView: TextView
    private lateinit var previousImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quran_details)

        val suraName = intent.getStringExtra(Constants.SURA_NAME)
        val suraNumber = intent.getIntExtra(Constants.SURA_NUMBER, 0)

        initComponent()


        val suraDetails = readSuraDataFromFile(suraNumber)



        if (suraName != null) {
            fillViewsWithData(suraDetails,suraName)
        }

        previousImageButton.setOnClickListener(this)

    }

    private fun initComponent() {
        suraDetailsTextView = findViewById(R.id.quran_details_activity_sura_data_tv)
        suraNameTextView = findViewById(R.id.quran_details_activity_sura_name_tv)
        previousImageButton = findViewById(R.id.quran_details_activity_previous_image_button)
    }

    private fun readSuraDataFromFile(suraNumber: Int): String {
        val fileName = "sura_assets/${suraNumber + 1}.txt"
        return assets.open(fileName).bufferedReader()
            .use { it.readText().replace("\n", " \u06DD ") }
    }

    private fun fillViewsWithData(suraDetails: String, suraName: String) {
        suraDetailsTextView.text = suraDetails
        suraNameTextView.text = suraName
    }

    override fun onClick(view: View?) {
        val clickedViewId  = view?.id

        if(clickedViewId == R.id.quran_details_activity_previous_image_button){
            finish()
        }

    }


}