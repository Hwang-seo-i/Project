package com.example.project.Fragment

import com.example.project.PathColorListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import androidx.fragment.app.DialogFragment
import com.example.project.R

class PathColorFragment : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.path_color_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val blueRadioButton = view.findViewById<RadioButton>(R.id.btn_blue)
        val greenRadioButton = view.findViewById<RadioButton>(R.id.btn_green)
        val yellowRadioButton = view.findViewById<RadioButton>(R.id.btn_yellow)
        val redRadioButton = view.findViewById<RadioButton>(R.id.btn_red)
        val confirmButton = view.findViewById<Button>(R.id.dialog_yes_btn)

        confirmButton.setOnClickListener {
            val selectedColor = when {
                blueRadioButton.isChecked -> "파란색"
                greenRadioButton.isChecked -> "초록색"
                yellowRadioButton.isChecked -> "노란색"
                redRadioButton.isChecked -> "빨간색"
                else -> "기본값"
            }

            // 선택한 색상을 SettingActivity로 전달
            val listener = activity as? PathColorListener
            listener?.onPathColorSelected(selectedColor)

            dismiss()
        }
    }
}
