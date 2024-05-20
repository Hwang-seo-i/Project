package com.example.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.model.data.BoardDataClass
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class EditBoardViewModel : ViewModel() {

    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    private val _isEditing = MutableLiveData<Boolean>()
    val isEditing: LiveData<Boolean> get() = _isEditing

    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int> get() = _position

    fun setBoardData(boardData: BoardDataClass, position: Int, isEditing: Boolean) {
        _title.value = boardData.title
        _selectedDate.value = boardData.date
        _position.value = position
        _isEditing.value = isEditing
    }

    fun updateTitle(newTitle: String) {
        _title.value = newTitle
    }

    fun updateSelectedDate(newDate: String) {
        _selectedDate.value = newDate
    }

    fun getFormattedDate(year: Int, month: Int, dayOfMonth: Int): String {
        val selectedDate = Calendar.getInstance().apply {
            set(year, month, dayOfMonth)
        }
        return SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(selectedDate.time)
    }
}
