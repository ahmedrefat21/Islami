package com.bignerdranch.android.islami

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.bignerdranch.android.islami.constants.Constants

class HadeethDetailsActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var hadeethDetailsTextView: TextView
    private lateinit var hadeethNameTextView: TextView
    private lateinit var previousImageButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hadeeth_details)

        val hadeethNumber = intent.getIntExtra(Constants.HADEETH_NUMBER, 0)

        initComponent()

        val hadeethFileContent = readHadeethContentFromFile(hadeethNumber)
        val hadeethDetails = splitHadeethDetails(hadeethFileContent)
        val hadeethName = splitHadeethName(hadeethFileContent)


        fillViewsWithData(hadeethDetails, hadeethName)

        previousImageButton.setOnClickListener(this)

    }


    private fun initComponent() {
        hadeethDetailsTextView = findViewById(R.id.hadeeth_details_activity_hadeeth_data_tv)
        hadeethNameTextView = findViewById(R.id.hadeeth_details_activity_hadeeth_name_tv)
        previousImageButton = findViewById(R.id.hadeeth_details_activity_previous_image_button)
    }

    private fun readHadeethContentFromFile(hadeethNumber: Int): String {
        val fileName = "hadeeth_assets/h${hadeethNumber + 1}.txt"
        return assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun splitHadeethDetails(hadeethFileContent: String): String {
        return hadeethFileContent.substring(hadeethFileContent.indexOf('\n') + 1)
    }

    private fun splitHadeethName(hadeethFileContent: String): String {
        return hadeethFileContent.lines()[0]
    }

    private fun fillViewsWithData(hadeethDetails: String, hadeethName: String) {
        hadeethDetailsTextView.text = hadeethDetails
        hadeethNameTextView.text = hadeethName
    }

    override fun onClick(view: View?) {
        val clickedViewId = view?.id

        if (clickedViewId == R.id.hadeeth_details_activity_previous_image_button) {
            finish()
        }

    }


}
