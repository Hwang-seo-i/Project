package com.example.project.View.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.View.activities.board.BoardActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val settingPageButton: Button = findViewById(R.id.btnSettings)
        settingPageButton.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        val boardPageButton: Button = findViewById(R.id.btnBoard)
        boardPageButton.setOnClickListener {
            val intent = Intent(this, BoardActivity::class.java)
            startActivity(intent)
        }

        val filePageButton: Button = findViewById(R.id.btnFile)
        filePageButton.setOnClickListener {
            val intent = Intent(this, FileActivity::class.java)
            startActivity(intent)
        }

        val mapPageButton: Button = findViewById(R.id.btnMap)
        mapPageButton.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
        }

        val httpPageButton: Button = findViewById(R.id.btnHttp)
        httpPageButton.setOnClickListener {
            val intent = Intent(this, HttpActivity::class.java)
            startActivity(intent)
        }
    }
}
