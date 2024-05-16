package com.example.project.board

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.*

class EditBoardActivity : AppCompatActivity() {

    private lateinit var titleEditText: TextInputEditText
    private lateinit var selectedDateTextView: TextInputEditText
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_board)

        val titleTextView: TextView = findViewById(R.id.title_text_view)
        titleTextView.text = getString(R.string.layout_edit_board)

        titleEditText = findViewById(R.id.text_input_title)
        selectedDateTextView = findViewById(R.id.text_selected_date)

        val boardData = intent.getSerializableExtra("boardData") as BoardDataClass
        val position = intent.getIntExtra("position", -1)

        titleEditText.setText(boardData.title)
        selectedDateTextView.setText(boardData.date)

        // Set up the date picker dialog
        val calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
                selectedDateTextView.setText(date)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        // Show the date picker dialog when the date text view is clicked
        selectedDateTextView.setOnClickListener {
            datePickerDialog?.show()
        }

        val saveButton: Button = findViewById(R.id.btnsave)
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val selectedDate = selectedDateTextView.text.toString().trim()

            if (title.isNotEmpty() && selectedDate.isNotEmpty()) {
                val returnIntent = Intent()
                returnIntent.putExtra("title_board", title)
                returnIntent.putExtra("text_selected_date", selectedDate)
                returnIntent.putExtra("position", position)
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }
    }
}