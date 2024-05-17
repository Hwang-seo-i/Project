package com.example.project.View.fragments

import android.content.Context
import android.graphics.Point
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.DialogFragment
import com.example.project.R

class PathSearchFragment : DialogFragment() {

    private lateinit var radioGroup: RadioGroup

    interface RouteGuideListener {
        var pathSearchLayout: LinearLayout

        fun onLocationSelected(position: String)
    }

    private var listener: RouteGuideListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = context as? RouteGuideListener
            ?: throw ClassCastException("$context must implement LocationSelectListener")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.path_search_option, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        radioGroup = view.findViewById(R.id.radio_group)

        view.findViewById<AppCompatButton>(R.id.dialog_yes_btn).setOnClickListener {
            val seletedRadioButtonId = radioGroup.checkedRadioButtonId
            val seletedRadioButton = view.findViewById<RadioButton>(seletedRadioButtonId)
            val position = seletedRadioButton.text.toString()

        }

        view.findViewById<AppCompatButton>(R.id.dialog_no_btn).setOnClickListener {
            dismiss()
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
        titleTextView?.text = getString(R.string.path_search)
    }
}