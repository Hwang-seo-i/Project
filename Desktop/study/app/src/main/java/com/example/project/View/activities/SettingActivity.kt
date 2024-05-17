package com.example.project.View.activities

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.View.fragments.LocationSelectDialogFragment
import com.example.project.View.fragments.PathColorFragment
import com.example.project.View.fragments.VoiceGuideDialogFragment
import com.example.project.viewmodel.SettingViewModel

class SettingActivity : AppCompatActivity(),
    LocationSelectDialogFragment.LocationSelectListener,
    PathColorFragment.PathColorListener {

    private lateinit var carNumberInput: TextView
    private lateinit var positionText: LinearLayout
    private lateinit var autoInputLayout: LinearLayout
    private lateinit var voiceGuideLayout: LinearLayout
    private lateinit var pathSearchLayout: LinearLayout
    private lateinit var pathColorText: LinearLayout
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var automaticMapSwitch: Switch
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var mapInformationSwitch: Switch
    private lateinit var mapStyleLayout: LinearLayout
    private lateinit var dayandnight: LinearLayout

    private val settingViewModel: SettingViewModel by viewModels()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val titleTextView: TextView = findViewById(R.id.title_text_view)
        titleTextView.text = getString(R.string.setting)

        val backButton: ImageView = findViewById(R.id.backIcon)
        backButton.setOnClickListener {
            finish()
        }

        positionText = findViewById(R.id.location_select_layout)
        carNumberInput = findViewById(R.id.CarNumberInput)
        autoInputLayout = findViewById(R.id.auto_input_layout)
        voiceGuideLayout = findViewById(R.id.voice_guide_layout)
        pathSearchLayout = findViewById(R.id.path_search_layout)
        pathColorText = findViewById(R.id.path_color_layout)
        automaticMapSwitch = findViewById(R.id.automatic_map_witch)
        mapInformationSwitch = findViewById(R.id.map_information_witch)
        mapStyleLayout = findViewById(R.id.map_style_layout)
        dayandnight = findViewById(R.id.day_night_mode_layout)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        settingViewModel.carNumber.observe(this) { carNumber ->
            carNumberInput.text = carNumber
        }

        settingViewModel.automaticMap.observe(this) { isChecked ->
            automaticMapSwitch.isChecked = isChecked
        }

        settingViewModel.location.observe(this) { location ->
            positionText.findViewById<TextView>(R.id.positionText).text = location
        }

        settingViewModel.mapInformation.observe(this) { isChecked ->
            mapInformationSwitch.isChecked = isChecked
        }

        settingViewModel.pathColor.observe(this) { color ->
            pathColorText.findViewById<TextView>(R.id.path_color_options).text = color
        }
    }

    private fun setupListeners() {
        carNumberInput.setOnClickListener {
            val inputEdit = EditText(this)
            inputEdit.hint = "차량 번호 입력"

            val dialog = AlertDialog.Builder(this)
                .setTitle("차량 번호 입력")
                .setView(inputEdit)
                .setPositiveButton("확인") { _, _ ->
                    settingViewModel.setCarNumber(inputEdit.text.toString())
                }
                .setNegativeButton("취소", null)
                .create()

            dialog.show()
        }

        autoInputLayout.setOnClickListener {
            Toast.makeText(applicationContext, "동태 자동입력 클릭", Toast.LENGTH_SHORT).show()
        }

        positionText.setOnClickListener {
            val dialogFragment = LocationSelectDialogFragment()
            dialogFragment.show(supportFragmentManager, "LocationSelectDialogFragment")
        }

        voiceGuideLayout.setOnClickListener {
            val dialogFragment = VoiceGuideDialogFragment()
            dialogFragment.show(supportFragmentManager, "VoiceGuideDialogFragment")
        }

        pathSearchLayout.setOnClickListener {
            val dialogFragment = VoiceGuideDialogFragment()
            dialogFragment.show(supportFragmentManager, "RouteGuideDialogColorFragment")
        }

        pathColorText.setOnClickListener {
            val dialogFragment = PathColorFragment()
            dialogFragment.show(supportFragmentManager, "PathColorFragment")
        }

        automaticMapSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.setAutomaticMap(isChecked)
        }

        mapInformationSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.setMapInformation(isChecked)
        }

        mapStyleLayout.setOnClickListener {
            Toast.makeText(applicationContext, "지도스타일 클릭", Toast.LENGTH_SHORT).show()
        }

        dayandnight.setOnClickListener {
            Toast.makeText(applicationContext, "주간/야간모드 클릭", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onLocationSelected(position: String) {
        settingViewModel.setLocation(position)
    }

    override fun onPathColorSelected(colorName: String) {
        settingViewModel.setPathColor(colorName)
    }
}
