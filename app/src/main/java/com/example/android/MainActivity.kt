package com.example.android

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var age: EditText
    private lateinit var woman: RadioButton
    private lateinit var man: RadioButton
    private lateinit var resume: Button
    private lateinit var clear: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.name)
        age = findViewById(R.id.age)
        woman = findViewById(R.id.woman)
        man = findViewById(R.id.man)
        resume = findViewById(R.id.resume)
        clear = findViewById(R.id.clear)

        woman.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && man.isChecked) {
                man.isChecked = false
            }
        }

        man.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked && woman.isChecked) {
                woman.isChecked = false
            }
        }

        resume.setOnClickListener {
            val inputName = name.text.toString()
            val inputAge = age.text.toString()

            // Проверка введенных данных
            if (inputName.isBlank() || inputAge.isBlank()) {
                showAlertDialog("Ошибка", "Пожалуйста, заполните все данные")
            } else if (!inputName.matches(Regex("[a-zA-Zа-яА-Я]+"))) {
                showAlertDialog("Ошибка", "Пожалуйста, введите корректное имя")
            } else {
                val userAge = inputAge.toIntOrNull()
                if (userAge == null || userAge <= 0 || userAge >= 150) {
                    showAlertDialog("Ошибка", "Пожалуйста, введите корректный возраст")
                } else if (!woman.isChecked && !man.isChecked) {
                    showAlertDialog("Ошибка", "Пожалуйста, выберите пол")
                } else {
                    val selectedGender = if (woman.isChecked) "женский" else "мужской"
                    val userInfoBundle = Bundle().apply {
                        putString("name", inputName)
                        putInt("age", userAge)
                        putString("gender", selectedGender)
                    }
                    val registrationIntent = Intent(this, Registration::class.java).apply {
                        putExtras(userInfoBundle)
                    }
                    startActivity(registrationIntent)
                }
            }
        }

        clear.setOnClickListener {
            name.text.clear()
            age.text.clear()
            woman.isChecked = false
            man.isChecked = false
        }
    }

    private fun showAlertDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }
}