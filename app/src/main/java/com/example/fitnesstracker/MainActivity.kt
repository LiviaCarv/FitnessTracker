  package com.example.fitnesstracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

  class MainActivity : AppCompatActivity() {
    private lateinit var layoutButton: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutButton = findViewById(R.id.lyt_imc)
        layoutButton.setOnClickListener{
            // navegar para a proxima tela
        }
    }
}