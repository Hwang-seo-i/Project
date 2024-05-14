package com.example.project

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.Fragment.LocationSelectDialogFragment
import com.example.project.Fragment.PathColorFragment
import com.example.project.Fragment.VoiceGuideDialogFragment
import com.example.project.Fragment.PathColorListener

class SettingActivity : AppCompatActivity(),
    LocationSelectDialogFragment.LocationSelectListener{

    private lateinit var carNumberInput: TextView
    private lateinit var locationSelectLayout: LinearLayout
    private lateinit var positionText: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var autoInputLayout: LinearLayout
    private lateinit var voiceGuideLayout: LinearLayout
    private lateinit var pathSearchLayout: LinearLayout
    private lateinit var pathColorLayout: LinearLayout
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var automaticMapSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mapInformationSwitch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)
        positionText = findViewById(R.id.positionText)
        carNumberInput = findViewById(R.id.CarNumberInput)
        locationSelectLayout = findViewById(R.id.location_select_layout)
        autoInputLayout = findViewById(R.id.auto_input_layout)
        voiceGuideLayout = findViewById(R.id.voice_guide_layout)
        pathSearchLayout = findViewById(R.id.path_search_layout)
        pathColorLayout = findViewById(R.id.path_color_layout)
        automaticMapSwitch = findViewById(R.id.automatic_map_witch)
        mapInformationSwitch = findViewById(R.id.map_information_witch)

        // 차량 번호 입력 설정
        carNumberInput.setOnClickListener {
            val inputEdit = EditText(this)
            inputEdit.hint = "차량 번호 입력"

            val dialog = AlertDialog.Builder(this)
                .setTitle("차량 번호 입력")
                .setView(inputEdit)
                .setPositiveButton("확인") { _, _ ->
                    carNumberInput.text = inputEdit.text.toString()
                    saveData() // 바로 저장
                }
                .setNegativeButton("취소", null)
                .create()

            dialog.show()
        }

        locationSelectLayout.setOnClickListener {
            //동태 위치 선택 클릭 시
            val dialogFragment = LocationSelectDialogFragment()
            dialogFragment.show(supportFragmentManager, "LocationSelectDialogFragment")
        }

        autoInputLayout.setOnClickListener {
            //동태 자동입력 클릭 시
            Toast.makeText(applicationContext, "동태 자동입력 클릭", Toast.LENGTH_SHORT).show()
        }

        voiceGuideLayout.setOnClickListener {
            // 안전운전 음성안내 클릭 시
            val dialogFragment = VoiceGuideDialogFragment()
            dialogFragment.show(supportFragmentManager, "VoiceGuideDialogFragment")
        }

        pathSearchLayout.setOnClickListener {
            //경로탐색옵션 클릭 시
            val dialogFragment = VoiceGuideDialogFragment()
            dialogFragment.show(supportFragmentManager, "RouteGuideDialogColorFragment")
        }

        pathColorLayout.setOnClickListener {
            //경로안내색상 클릭 시
            val dialogFragment = PathColorFragment()
            dialogFragment.show(supportFragmentManager, "PathColorFragment")
        }

        // 초기 데이터 복원
        restoreData()
        updatePositionText()

        automaticMapSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch is ON
                Toast.makeText(applicationContext, "Automatic Map is ON", Toast.LENGTH_SHORT).show()
            } else {
                // Switch is OFF
                Toast.makeText(applicationContext, "Automatic Map is OFF", Toast.LENGTH_SHORT).show()
            }
        }

        mapInformationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch is ON
                Toast.makeText(applicationContext, "Map Information is ON", Toast.LENGTH_SHORT).show()
            } else {
                // Switch is OFF
                Toast.makeText(applicationContext, "Map Information is OFF", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //차량 번호 입력
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

    override fun onLocationSelected(position: String) {
        // 위치 선택 결과 처리
        val editor = sharedPreferences.edit()
        editor.putString("position", position)
        editor.apply()
        updatePositionText()
    }

    private fun updatePositionText() {
        val position = sharedPreferences.getString("position", "왼쪽")
        positionText.text = position
    }

    private fun updateColorText() {
        val color = sharedPreferences.getString("path_color", "파란색")
        positionText.text = color
    }


//    override fun onPathColorSelected(pathColor: String) {
//        val editor = sharedPreferences.edit()
//        editor.putString("path_color", pathColor)
//        editor.apply()
//        updateColorText()
//    }
}
