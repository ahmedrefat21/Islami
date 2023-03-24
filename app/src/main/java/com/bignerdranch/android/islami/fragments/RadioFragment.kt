package com.bignerdranch.android.islami.fragments

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.bignerdranch.android.islami.API.QuranInterface
import com.bignerdranch.android.islami.R
import com.bignerdranch.android.islami.models.QuranAPIDataModel
import com.bignerdranch.android.islami.models.QuranList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


class RadioFragment : Fragment(), View.OnClickListener {

    lateinit var playImageButton: ImageButton
    lateinit var nextImageButton: ImageButton
    lateinit var previousImageButton: ImageButton
    lateinit var quranList: QuranList
    var isPLAYING = false
    var suraCount = 0
    var mediaPlayer: MediaPlayer? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent(view)
        requestQuranAudio()

        playImageButton.setOnClickListener(this)
        nextImageButton.setOnClickListener(this)
        previousImageButton.setOnClickListener(this)
    }

    fun initComponent(view: View) {
        playImageButton = view.findViewById(R.id.radio_fragment_play_image_button)
        nextImageButton = view.findViewById(R.id.radio_fragment_next_image_button)
        previousImageButton = view.findViewById(R.id.radio_fragment_previous_image_button)
    }

    private fun requestQuranAudio() {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.quran.com/api/v4/chapter_recitations/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val quranInterface = retrofit.create(QuranInterface::class.java)

        val call: Call<QuranList> = quranInterface.getQuran()

        call.enqueue(object : Callback<QuranList> {
            override fun onResponse(call: Call<QuranList>, response: Response<QuranList>) {
                quranList = response.body()!!
            }

            override fun onFailure(call: Call<QuranList>, t: Throwable) {
                Toast.makeText(context, t.stackTraceToString(), Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun playQuranAudio(suraIndex: Int) {
        mediaPlayer = MediaPlayer()
            try {
                mediaPlayer!!.setDataSource(
                    quranList?.audio_files?.get(suraIndex)?.audio_url ?: "null"
                )
                mediaPlayer!!.prepare()
                mediaPlayer!!.start()
            } catch (exception: IOException) {
                Toast.makeText(context, exception.stackTrace.toString(), Toast.LENGTH_LONG).show()
            }
        }

    override fun onClick(v: View?) {
        val clickedImageButtonId = v?.id

        if (clickedImageButtonId == R.id.radio_fragment_play_image_button) {

            if (!isPLAYING) {
                playImageButton.setImageResource(R.drawable.radio_pause_button)
                playQuranAudio(suraCount)
                isPLAYING = true
            } else {
                playImageButton.setImageResource(R.drawable.radio_play_button)
                mediaPlayer?.stop()
                isPLAYING = false
            }

        } else if (clickedImageButtonId == R.id.radio_fragment_next_image_button) {
            if (suraCount < 114) {
                suraCount++
            } else {
                suraCount = 0
            }
            mediaPlayer?.stop()
            if(isPLAYING){
                playQuranAudio(suraCount)
            }

        } else if (clickedImageButtonId == R.id.radio_fragment_previous_image_button) {
            if (suraCount > 0) {
                suraCount--
            } else {
                suraCount = 113
            }
            mediaPlayer?.stop()

            if(isPLAYING){
                playQuranAudio(suraCount)
            }
        }
    }
}

