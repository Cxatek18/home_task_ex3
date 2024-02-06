package com.team.phonebook.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.team.phonebook.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, MainFragment.newInstance())
            .addToBackStack(MainFragment.NAME_FRAGMENT)
            .commit()

    }
}