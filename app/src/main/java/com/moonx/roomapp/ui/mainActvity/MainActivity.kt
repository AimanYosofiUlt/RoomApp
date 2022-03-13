package com.moonx.roomapp.ui.mainActvity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moonx.roomapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }
}