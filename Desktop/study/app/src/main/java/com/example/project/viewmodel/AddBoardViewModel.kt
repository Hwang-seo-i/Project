// AddBoardViewModel.kt
package com.example.project.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project.model.data.BoardDataClass
import com.example.project.model.repository.BoardRepository

class AddBoardViewModel : ViewModel() {

    private val boardRepository = BoardRepository()

    fun saveBoardData(title: String, selectedDate: String) {
        val newBoardData = BoardDataClass(0, title, selectedDate)
        boardRepository.addBoardData(newBoardData)
    }
}
