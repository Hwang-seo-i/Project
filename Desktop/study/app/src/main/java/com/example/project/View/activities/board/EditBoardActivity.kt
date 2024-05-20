package com.example.project.View.activities.board

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.project.R
import com.example.project.model.data.BoardDataClass
import com.example.project.viewmodel.EditBoardViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class EditBoardActivity : AppCompatActivity() {

    private lateinit var titleEditText: TextInputEditText
    private lateinit var selectedDateTextView: TextInputEditText
    private var datePickerDialog: DatePickerDialog? = null

    private val viewModel: EditBoardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_board)

        val titleTextView: TextView = findViewById(R.id.title_text_view)
        titleTextView.text = getString(R.string.layout_edit_board)

        titleEditText = findViewById(R.id.text_input_title)
        selectedDateTextView = findViewById(R.id.text_selected_date)

        val boardData = intent.getSerializableExtra("boardData") as BoardDataClass
        val position = intent.getIntExtra("position", -1)
        val isEditing = intent.getBooleanExtra("isEditing", false)

        viewModel.setBoardData(boardData, position, isEditing)

        viewModel.title.observe(this, Observer { title ->
            titleEditText.setText(title)
        })

        viewModel.selectedDate.observe(this, Observer { date ->
            selectedDateTextView.setText(date)
        })

        val saveButton: Button = findViewById(R.id.btnsave)
        saveButton.text = if (isEditing) "수정" else "등록"
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val selectedDate = selectedDateTextView.text.toString().trim()

            if (title.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
            } else {
                val returnIntent = Intent().apply {
                    putExtra("title_board", title)
                    putExtra("text_selected_date", selectedDate)
                    putExtra("position", position)
                }
                setResult(RESULT_OK, returnIntent)
                finish()
            }
        }

        val calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val date = viewModel.getFormattedDate(year, month, dayOfMonth)
                viewModel.updateSelectedDate(date)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        selectedDateTextView.setOnClickListener {
            datePickerDialog?.show()
        }
    }
}
