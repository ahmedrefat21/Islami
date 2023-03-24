package com.bignerdranch.android.islami

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bignerdranch.android.islami.fragments.HadeethFragment
import com.bignerdranch.android.islami.fragments.QuranFragment
import com.bignerdranch.android.islami.fragments.RadioFragment
import com.bignerdranch.android.islami.fragments.SebhaFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponent()

        if (savedInstanceState == null) {
            showFragment(QuranFragment())
        }



        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_quran -> {
                    showFragment(QuranFragment())
                    true
                }
                R.id.menu_hadeeth -> {
                    showFragment(HadeethFragment())
                    true
                }
                R.id.menu_sebha -> {
                    showFragment(SebhaFragment())
                    true
                }
                R.id.menu_radio -> {
                    showFragment(RadioFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun initComponent() {
        bottomNavigationView = findViewById(R.id.main_activity_bottom_navigation_view)

    }

    private fun showFragment(requiredFragment: Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_activity_fragment_container, requiredFragment).commit()
    }


}