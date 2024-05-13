package com.example.project.Fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.project.R

class VoiceGuideDialogFragment : DialogFragment() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_voice_guide_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedPreferences = requireActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE)

        val checkButton = view.findViewById<CheckBox>(R.id.checkButton)
        val checkButton2 = view.findViewById<CheckBox>(R.id.checkButton2)
        val checkButton3 = view.findViewById<CheckBox>(R.id.checkButton3)
        val checkButton4 = view.findViewById<CheckBox>(R.id.checkButton4)

        // 기존 설정값 불러오기
        checkButton.isChecked = sharedPreferences.getBoolean("checkButton1", true)
        checkButton2.isChecked = sharedPreferences.getBoolean("checkButton2", false)
        checkButton3.isChecked = sharedPreferences.getBoolean("checkButton3", false)
        checkButton4.isChecked = sharedPreferences.getBoolean("checkButton4", false)

        val editor = sharedPreferences.edit()

        //확인버튼 클릭 시 저장
        view.findViewById<AppCompatButton>(R.id.dialog_yes_btn).setOnClickListener {
            // 체크박스 상태 저장
            editor.putBoolean("checkButton1", checkButton.isChecked)
            editor.putBoolean("checkButton2", checkButton2.isChecked)
            editor.putBoolean("checkButton3", checkButton3.isChecked)
            editor.putBoolean("checkButton4", checkButton4.isChecked)
            editor.apply()

            dismiss() // 다이얼로그 닫기
        }
        view.findViewById<AppCompatButton>(R.id.dialog_no_btn).setOnClickListener {
            dismiss() // 다이얼로그 닫기
        }
    }

}
