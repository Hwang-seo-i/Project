package com.example.project.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.project.R

class LocationSelectDialogFragment : DialogFragment() {

    private lateinit var radioGroup: RadioGroup

    // 콜백 인터페이스 정의
    interface LocationSelectListener {
        var voiceGuideLayout: LinearLayout

        fun onLocationSelected(position: String)
    }

    private var listener: LocationSelectListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? LocationSelectListener
            ?: throw ClassCastException("$context must implement LocationSelectListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_location_select, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioGroup = view.findViewById(R.id.RadioGroup)

        view.findViewById<AppCompatButton>(R.id.dialog_yes_btn).setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)
            val position = selectedRadioButton.text.toString()

            // 선택된 위치를 Activity에 알림
            listener?.onLocationSelected(if (position.contains("왼쪽")) "왼쪽" else "오른쪽")
            dismiss()
        }

        view.findViewById<AppCompatButton>(R.id.dialog_no_btn).setOnClickListener {
            dismiss()
        }
    }
}
