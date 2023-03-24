package com.bignerdranch.android.islami.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bignerdranch.android.islami.R


class SebhaFragment : Fragment(), View.OnClickListener {


    private lateinit var tasbeehCountTextView: TextView
    private lateinit var tasbeehButton: Button
    private lateinit var sebaBodyImageView: ImageView
    private lateinit var tasbeehString: Array<String>
    private var tasbeehCount = 0
    private var tasbeehStringIndex = 0
    private var rotationAngle = 10.0f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sebha, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent(view)
        tasbeehString = resources.getStringArray(R.array.tasbeeh_array)

        tasbeehCountTextView.text = resources.getStringArray(R.array.numbers)[tasbeehCount]
        tasbeehButton.text = tasbeehString[tasbeehStringIndex]

        tasbeehButton.setOnClickListener(this)

    }

    private fun initComponent(view: View) {
        tasbeehCountTextView = view.findViewById(R.id.sebha_fragment_tasbeeh_count_tv)
        tasbeehButton = view.findViewById(R.id.sebha_fragment_count_button)
        sebaBodyImageView = view.findViewById(R.id.sebha_fragment_sebha_body)
    }

    override fun onClick(view: View?) {
        val clickedButton = view as Button?
        val clickedButtonId = view?.id

        if (clickedButtonId == R.id.sebha_fragment_count_button) {
            tasbeehCountButtoClicked()
            rotateSebhaBody()
        }
    }

    private fun tasbeehCountButtoClicked() {

        if (tasbeehCount == 33) {
            tasbeehCount = -1
            if (tasbeehStringIndex == 2) {
                tasbeehStringIndex = 0
            } else {
                tasbeehStringIndex++
            }
        }
        tasbeehButton.text = tasbeehString[tasbeehStringIndex]
        tasbeehCount++
        tasbeehCountTextView.text = resources.getStringArray(R.array.numbers)[tasbeehCount]
    }

    private fun rotateSebhaBody() {
        sebaBodyImageView.animate().rotation(rotationAngle)
        sebaBodyImageView.animate().duration = 500
        sebaBodyImageView.animate().start()

        if (rotationAngle > 3000) {
            rotationAngle = 0.0f
        }
        rotationAngle += 10.0f
    }


}