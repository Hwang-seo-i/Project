package com.example.project

import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val carNumberInput = findViewById<TextView>(R.id.CarNumberInput)

        carNumberInput.setOnClickListener {
            val inputEdit = EditText(this)
            inputEdit.hint = "차량 번호 입력"

            // 차량 번호 입력 팝업창
            AlertDialog.Builder(this).apply {
                setTitle("차량 번호 입력")
                setView(inputEdit)
                setPositiveButton("확인") { dialog, which ->
                    carNumberInput.text = inputEdit.text.toString()
                }
                setNegativeButton("취소", null)
                show()
            }
        }
    }
}