package com.example.project

import android.app.Activity
import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.Fragment.LocationSelectDialogFragment
import com.example.project.Fragment.VoiceGuideDialogFragment

class SettingActivity : AppCompatActivity(), LocationSelectDialogFragment.LocationSelectListener {

    private lateinit var carNumberInput: TextView
    private lateinit var locationSelectLayout: LinearLayout
    private lateinit var positionText: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var autoInputLayout: LinearLayout
    override lateinit var voiceGuideLayout: LinearLayout
    private lateinit var voiceListLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)

        positionText = findViewById(R.id.positionText)
        carNumberInput = findViewById(R.id.CarNumberInput)
        locationSelectLayout = findViewById(R.id.location_select_layout)
        autoInputLayout = findViewById(R.id.auto_input_layout)
        voiceListLayout = findViewById(R.id.voice_list_layout)

        // 차량 번호 입력 설정
        carNumberInput.setOnClickListener {
            val inputEdit = EditText(this)
            inputEdit.hint = "차량 번호 입력"

            AlertDialog.Builder(this).apply {
                setTitle("차량 번호 입력")
                setView(inputEdit)
                setPositiveButton("확인") { _, _ ->
                    carNumberInput.text = inputEdit.text.toString()
                    saveData() // 바로 저장
                }
                setNegativeButton("취소", null)
                show()
            }
        }

        locationSelectLayout.setOnClickListener {
            //동태 위치 선택 클릭 시
            val dialogFragment = LocationSelectDialogFragment()
            dialogFragment.show(supportFragmentManager, "LocationSelectDialogFragment")
        }

        autoInputLayout.setOnClickListener {
            //동태 자동입력 클릭 시
            ToastMessage.showCustomToast(applicationContext, "동태 자동입력 클릭")
        }

        voiceListLayout.setOnClickListener {
            // 안전운전 음성안내 클릭 시
            val dialogFragment = VoiceGuideDialogFragment()
            dialogFragment.show(supportFragmentManager, "VoiceGuideDialogFragment")
        }
    }

    //동태 위치 정보 저장
    private fun saveData() {
        val pref: SharedPreferences = getSharedPreferences("car_number", Activity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref.edit()

        val carNumber = carNumberInput.text.toString()

        editor.putString("car number", carNumber)
        editor.apply()
    }

    // 화면 없어지면 실행
    override fun onStop() {
        super.onStop()
        saveData()
    }

    // 정보 복원
    private fun restoreData() {
        val pref: SharedPreferences = getSharedPreferences("car_number", Activity.MODE_PRIVATE)
        val carNumber = pref.getString("car number", null)
        carNumber?.let {
            carNumberInput.text = it
        }
    }

    // 화면 보여지면 실행
    override fun onResume() {
        super.onResume()
        restoreData()
    }

    private fun updatePositionText() {
        val position = sharedPreferences.getString("position", "왼쪽")
        positionText.text = position
    }

    override fun onLocationSelected(position: String) {
        // 위치 선택 결과 처리
        val editor = sharedPreferences.edit()
        editor.putString("position", position)
        editor.apply()
        updatePositionText()
    }
}
