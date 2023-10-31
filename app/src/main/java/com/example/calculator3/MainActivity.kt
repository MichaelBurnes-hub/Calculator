package com.example.calculator3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity

class MainActivity : ComponentActivity() {

    private var tvInput: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)

    }

    fun onDigit(view: View){
        tvInput?.append((view as Button).text)
    }
    fun onClear(view: View){
        tvInput?.text = ""
    }
}