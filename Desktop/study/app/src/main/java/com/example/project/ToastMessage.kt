package com.example.project

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast

class ToastMessage {

    companion object {
        fun showCustomToast(context: Context, message: String) {
            val inflater = LayoutInflater.from(context)
            val layout = inflater.inflate(R.layout.activity_toast_message, null)

            val textView = layout.findViewById<TextView>(R.id.textboard)
            textView.text = message

            with(Toast(context)) {
                duration = Toast.LENGTH_SHORT
                view = layout
                show()
            }
        }
    }
}
