package com.example.project.viewmodel

import androidx.lifecycle.ViewModel
import com.example.project.model.data.BoardDataClass
import com.example.project.model.repository.BoardRepository

// AddBoardActivity 화면 데이터 처리
class AddBoardViewModel : ViewModel() {

    // 게시판 데이터를 관리하는 Repository
    private val boardRepository = BoardRepository()

    // 게시판 데이터를 저장하는 메서드
    fun saveBoardData(title: String, selectedDate: String) {
        // 새로운 게시판 데이터 생성
        val newBoardData = BoardDataClass(0, title, selectedDate)
        // Repository를 통해 게시판 데이터 추가
        boardRepository.addBoardData(newBoardData)
    }
}