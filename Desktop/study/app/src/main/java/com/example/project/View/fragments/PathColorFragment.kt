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
import com.example.project.model.repository.PreferenceManager
import com.example.project.R

class PathColorFragment : DialogFragment() {

    private lateinit var radioGroup: RadioGroup

    interface PathColorListener {
        fun onPathColorSelected(colorName: String)
    }

    private var listener: PathColorListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? PathColorListener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.path_color_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioGroup = view.findViewById(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.radioButton -> {
                    PreferenceManager.setString(requireContext(), "pathColor", "빨간색")
                    listener?.onPathColorSelected("빨간색")
                }
                R.id.radioButton2 -> {
                    PreferenceManager.setString(requireContext(), "pathColor", "파란색")
                    listener?.onPathColorSelected("파란색")
                }
                R.id.radioButton3 -> {
                    PreferenceManager.setString(requireContext(), "pathColor", "초록색")
                    listener?.onPathColorSelected("초록색")
                }
                R.id.radioButton4 -> {
                    PreferenceManager.setString(requireContext(), "pathColor", "노란색")
                    listener?.onPathColorSelected("노란색")
                }
            }
        }

        setRadioButtonCheckedState()

        view.findViewById<AppCompatButton>(R.id.dialog_yes_btn).setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)
            val colorName = selectedRadioButton.text.toString()
            if(colorName == "빨간색") {
                PreferenceManager.setString(requireContext(), "pathColor", "빨간색")
            } else if(colorName == "파란색") {
                PreferenceManager.setString(requireContext(), "pathColor", "파란색")
            } else if(colorName == "초록색") {
                PreferenceManager.setString(requireContext(), "pathColor", "초록색")
            } else if(colorName == "노란색") {
                PreferenceManager.setString(requireContext(), "pathColor", "노란색")
            }
            listener?.onPathColorSelected(colorName)
            dismiss()
        }

        view.findViewById<AppCompatButton>(R.id.dialog_no_btn).setOnClickListener {
            dismiss()
        }
    }

    private fun setRadioButtonCheckedState() {
        var savedColor = PreferenceManager.getString(requireContext(), "pathColor")
        if (savedColor == null) {
            savedColor = "빨간색"
            PreferenceManager.setString(requireContext(), "pathColor", savedColor)
        }
        when (savedColor) {
            "빨간색" -> view?.findViewById<RadioButton>(R.id.btn_red)?.isChecked = true
            "파란색" -> view?.findViewById<RadioButton>(R.id.btn_blue)?.isChecked = true
            "초록색" -> view?.findViewById<RadioButton>(R.id.btn_green)?.isChecked = true
            "노란색" -> view?.findViewById<RadioButton>(R.id.btn_yellow)?.isChecked = true
        }
    }
    override fun onStart() {
        super.onStart()
        dialog?.window?.let { window ->
            val size = Point()
            val display = window.windowManager.defaultDisplay
            display.getSize(size)

            val radioButtonCount = radioGroup.childCount

            val heightPerButton = size.y * 0.13
            val height = (radioButtonCount * heightPerButton).toInt()

            val width = (size.x * 0.5).toInt()

            window.setLayout(width, height)
            window.setGravity(Gravity.CENTER)
        }
        val titleTextView: TextView? = view?.findViewById(R.id.title_text_view)
        titleTextView?.text = getString(R.string.path_color)
    }
}