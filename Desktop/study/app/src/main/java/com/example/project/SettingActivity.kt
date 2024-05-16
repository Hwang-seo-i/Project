package com.example.project

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.project.Fragment.LocationSelectDialogFragment
import com.example.project.Fragment.PathColorFragment
import com.example.project.Fragment.VoiceGuideDialogFragment

class SettingActivity : AppCompatActivity(),
    LocationSelectDialogFragment.LocationSelectListener,
    PathColorFragment.PathColorListener {

    private lateinit var carNumberInput: TextView
    private lateinit var positionText: TextView
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var autoInputLayout: LinearLayout
    private lateinit var voiceGuideLayout: LinearLayout
    private lateinit var pathSearchLayout: LinearLayout
    private lateinit var pathColorText: TextView
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var automaticMapSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mapInformationSwitch: Switch
    private lateinit var mapStyleLayout: LinearLayout
    private lateinit var dayandnight: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val backButton: ImageView = findViewById(R.id.backIcon)
        backButton.setOnClickListener {
            finish()
        }

        sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE)
        positionText = findViewById(R.id.positionText)
        carNumberInput = findViewById(R.id.CarNumberInput)
        autoInputLayout = findViewById(R.id.auto_input_layout)
        voiceGuideLayout = findViewById(R.id.voice_guide_layout)
        pathSearchLayout = findViewById(R.id.path_search_layout)
        pathColorText = findViewById(R.id.path_color_options)
        automaticMapSwitch = findViewById(R.id.automatic_map_witch)
        mapInformationSwitch = findViewById(R.id.map_information_witch)
        mapStyleLayout = findViewById(R.id.map_style_layout)
        dayandnight = findViewById(R.id.day_night_mode_layout)

        setinit()

        // 차량 번호 입력 설정
        carNumberInput.setOnClickListener {
            val inputEdit = EditText(this)
            inputEdit.hint = "차량 번호 입력"

            val dialog = AlertDialog.Builder(this)
                .setTitle("차량 번호 입력")
                .setView(inputEdit)
                .setPositiveButton("확인") { _, _ ->
                    carNumberInput.text = inputEdit.text.toString()
                    PreferenceManager.setString(applicationContext, "carNumber", carNumberInput.text.toString())
                }
                .setNegativeButton("취소", null)
                .create()

            dialog.show()
        }

        positionText.setOnClickListener {
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

        pathColorText.setOnClickListener {
            //경로안내색상 클릭 시
            val dialogFragment = PathColorFragment()
            dialogFragment.show(supportFragmentManager, "PathColorFragment")
        }

        automaticMapSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch is ON
                Toast.makeText(applicationContext, "Automatic Map is ON", Toast.LENGTH_SHORT).show()
            } else {
                // Switch is OFF
                Toast.makeText(applicationContext, "Automatic Map is OFF", Toast.LENGTH_SHORT).show()
            }
            PreferenceManager.setBoolean(applicationContext, "automatic", isChecked)
        }

        mapInformationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Switch is ON
                Toast.makeText(applicationContext, "Map Information is ON", Toast.LENGTH_SHORT).show()
            } else {
                // Switch is OFF
                Toast.makeText(applicationContext, "Map Information is OFF", Toast.LENGTH_SHORT).show()
            }
            PreferenceManager.setBoolean(applicationContext, "mapInformation", isChecked)
        }

        mapStyleLayout.setOnClickListener {
            //지도스타일 클릭 시
            Toast.makeText(applicationContext, "지도스타일 클릭", Toast.LENGTH_SHORT).show()
        }

        dayandnight.setOnClickListener {
            //주간/야간모드 클릭 시
            Toast.makeText(applicationContext, "주간/야간모드 클릭", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setinit(){
        carNumberInput.text = PreferenceManager.getString(applicationContext, "carNumber")
        automaticMapSwitch.setChecked(PreferenceManager.getBoolean(applicationContext, "automatic"))
        positionText.text = PreferenceManager.getString(applicationContext, "location")
        mapInformationSwitch.setChecked(PreferenceManager.getBoolean(applicationContext, "mapInformation"))
        pathColorText.text = PreferenceManager.getString(applicationContext, "pathColor")
    }

    override fun onLocationSelected(position: String) {
        positionText.text = position
        PreferenceManager.setString(applicationContext, "location", position)
    }

    override fun onPathColorSelected(colorName: String) {
        pathColorText.text = colorName
        PreferenceManager.setString(applicationContext, "pathColor", colorName)
    }
}
