package com.example.project.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project.model.data.BoardDataClass

class BoardViewModel : ViewModel() {

    private val _boardDataList = MutableLiveData<MutableList<BoardDataClass>>()
    val boardDataList: LiveData<MutableList<BoardDataClass>>
        get() = _boardDataList

    init {
        _boardDataList.value = mutableListOf(
            BoardDataClass(1, "제목 1", "2023-04-01"),
            BoardDataClass(2, "제목 2", "2023-04-02"),
            BoardDataClass(3, "제목 3", "2023-04-03"),
            BoardDataClass(4, "제목 4", "2023-04-04"),
            BoardDataClass(5, "제목 5", "2023-04-05")
        )
    }

    fun addBoardData(boardData: BoardDataClass) {
        val newList = _boardDataList.value ?: mutableListOf()
        newList.add(boardData)
        _boardDataList.value = newList
    }

    fun editBoardData(position: Int, editedBoardData: BoardDataClass) {
        val newList = _boardDataList.value ?: mutableListOf()
        if (position in newList.indices) {
            newList[position] = editedBoardData
            _boardDataList.value = newList
        }
    }
}
