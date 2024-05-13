package com.example.project.Fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import com.example.project.R

class RouteGuideDialogColorFragment : DialogFragment() {
    private lateinit var radioGroup: RadioGroup

    interface LocationSelectListener {
        var RadioGroup: RadioGroup

        fun onLocationSelected(position: String)
    }

    private var listener: LocationSelectListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.route_guide_color, container, false)
    }


}