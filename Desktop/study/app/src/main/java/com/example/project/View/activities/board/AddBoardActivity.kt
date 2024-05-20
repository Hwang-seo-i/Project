package com.example.project.View.activities.board

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.project.R
import com.example.project.viewmodel.AddBoardViewModel
import com.google.android.material.textfield.TextInputEditText
import java.util.Calendar

class AddBoardActivity : AppCompatActivity() {

    // 제목 입력
    private lateinit var titleEditText: TextInputEditText
    // 선택한 날짜 표시
    private lateinit var selectedDateTextView: TextInputEditText
    // 날짜 선택 다이얼로그
    private var datePickerDialog: DatePickerDialog? = null
    // 게시판 데이터 관리
    private val viewModel: AddBoardViewModel by viewModels()

    @SuppressLint("CutPasteId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_board)

        titleEditText = findViewById(R.id.text_input_title)
        selectedDateTextView = findViewById(R.id.text_selected_date)

        // 제목 설정
        val titleTextView: TextView = findViewById(R.id.title_text_view)
        titleTextView.text = getString(R.string.layout_add_board)

        // 선택한 날짜 클릭 리스너 설정
        val selectedDateEditText: TextInputEditText = findViewById(R.id.text_selected_date)
        selectedDateEditText.setOnClickListener {
            showDatePickerDialog()
        }

        // 저장 버튼 클릭 리스너 설정
        val saveButton: Button = findViewById(R.id.btnsave)
        saveButton.setOnClickListener {
            val title = titleEditText.text.toString().trim()
            val selectedDate = selectedDateTextView.text.toString().trim()

            // 제목과 선택한 날짜가 비어있지 않으면 결과를 반환하고 액티비티를 종료
            if (title.isEmpty() || selectedDate.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.saveBoardData(title, selectedDate)
                finish()
            }
        }
    }

    // 날짜 선택 Dialog 표시
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