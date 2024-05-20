package com.example.project.View.fragments

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.project.R
import com.example.project.model.repository.PreferenceManager

class LocationSelectDialogFragment : DialogFragment() {

    private lateinit var radioGroup: RadioGroup

    // 선택된 위치를 SettingActivity로 전달하는 인터페이스
    interface LocationSelectListener {
        fun onLocationSelected(position: String)
    }

    private var listener: LocationSelectListener? = null

    // Fragment가 연결될 때 리스너 연결
    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? LocationSelectListener
    }

    // activity_location_select inflate
    // xml 코드들을 객체화해 소스코드에서 사용
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_location_select, container, false)
    }

    // 뷰가 생성된 후 설정
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioGroup = view.findViewById(R.id.RadioGroup)

        // 저장된 위치에 따라 라디오 버튼의 체크 상태 설정
        setLocationCheckedState()

        view.findViewById<AppCompatButton>(R.id.dialog_yes_btn).setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)
            val position = selectedRadioButton.text.toString()
            if (position.equals("왼쪽")) {
                PreferenceManager.setString(requireContext(), "location", "왼쪽")
                listener?.onLocationSelected("왼쪽")
            } else {
                PreferenceManager.setString(requireContext(), "location", "오른쪽")
                listener?.onLocationSelected("오른쪽")
            }
            dismiss()
        }

        view.findViewById<AppCompatButton>(R.id.dialog_no_btn).setOnClickListener {
            dismiss()
        }
    }

    private fun setLocationCheckedState() {
        var savedlocation = PreferenceManager.getString(requireContext(), "location")
        if (savedlocation == null) {
            savedlocation = "왼쪽"
            PreferenceManager.setString(requireContext(), "location", "왼쪽")
        }
        when (savedlocation) {
            "왼쪽" -> radioGroup.check(R.id.radioButton)
            "오른쪽" -> radioGroup.check(R.id.radioButton2)
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val size = Point()
            val display = window.windowManager.defaultDisplay
            display.getSize(size)

            val width = (size.x * 0.5).toInt()
            val height = (size.y * 0.4).toInt()

            window.setLayout(width, height)
            window.setGravity(Gravity.CENTER)
        }
        val titleTextView: TextView? = view?.findViewById(R.id.title_text_view)
        titleTextView?.text = getString(R.string.location)
    }
}
