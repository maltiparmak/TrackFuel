package com.technowalker.trackfuel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.technowalker.trackfuel.R
import com.technowalker.trackfuel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }



}