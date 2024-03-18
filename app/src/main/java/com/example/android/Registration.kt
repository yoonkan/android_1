package com.example.android

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Registration : AppCompatActivity() {

    private lateinit var name_R: TextView
    private lateinit var age_R: TextView
    private lateinit var gender_R: TextView
    private lateinit var further: Button
    private lateinit var back: Button
    private lateinit var fon: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration)

        initViews()

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 0)
        val gender = intent.getStringExtra("gender")

        name_R.text = name
        age_R.text = age.toString()
        gender_R.text = gender

        further.setOnClickListener {
            navigateToWebsite()
        }

        back.setOnClickListener {
            finish()
        }

        fon.setOnClickListener {
            changeBackgroundColor()
        }
    }

    private fun initViews() {
        name_R = findViewById(R.id.name_R)
        age_R = findViewById(R.id.age_R)
        gender_R = findViewById(R.id.gender_R)
        further = findViewById(R.id.further)
        back = findViewById(R.id.back)
        fon = findViewById(R.id.fon)
    }

    private fun navigateToWebsite() {
        val registrationIntent = Intent(this, Website::class.java)
        startActivity(registrationIntent)
    }
    private fun changeBackgroundColor() {
        val randomColor = Color.rgb((0..255).random(), (0..255).random(), (0..255).random())
        val rootView = window.decorView.findViewById<ViewGroup>(android.R.id.content)
        rootView.setBackgroundColor(randomColor)
    }
}
