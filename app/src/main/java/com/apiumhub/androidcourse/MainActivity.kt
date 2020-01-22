package com.apiumhub.androidcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var innerCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val counter: TextView = findViewById<TextView>(R.id.counter)
        val button: Button = findViewById<Button>(R.id.button)

        counter.setText(innerCounter.toString())

        button.text = "Incremento"
        button.setOnClickListener {
            innerCounter++
            counter.setText(innerCounter.toString())
        }
    }
}
