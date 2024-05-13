package com.example.project.board

import android.widget.Toast
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar
import android.app.DatePickerDialog
import com.example.project.R

class AddBoardActivity : AppCompatActivity() {

    private lateinit var titleEditText: TextInputEditText
    private lateinit var selectedDateTextView: TextInputEditText
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_board)

        titleEditText = findViewById(R.id.text_input_title)
        selectedDateTextView = findViewById(R.id.text_selected_date)

        val calendarIcon: ImageButton = findViewById(R.id.calender_icon)
        calendarIcon.setOnClickListener {
            showDatePickerDialog()
        }

        val saveButton: Button = findViewById(R.id.btnsave)
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val selectedDate = selectedDateTextView.text.toString().trim()

            if (title.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            } else {
                val returnIntent = Intent()
                returnIntent.putExtra("title_board", title)
                returnIntent.putExtra("text_selected_date", selectedDate)
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }
    }

    private fun showDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        datePickerDialog = DatePickerDialog(this, { _, year, monthOfYear, dayOfMonth ->
            val selectedDate = "$year-${monthOfYear + 1}-$dayOfMonth"
            selectedDateTextView.setText(selectedDate)
        }, year, month, day)

        datePickerDialog?.show()
    }
}
