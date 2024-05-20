// BoardRepository.kt
package com.example.project.model.repository

import com.example.project.model.data.BoardDataClass

// 게시판 데이터 관리
class BoardRepository {
    // 게시판 데이터 목록
    private val boardDataList = mutableListOf<BoardDataClass>()

    // 게시판 데이터 추가
    fun addBoardData(boardData: BoardDataClass) {
        boardDataList.add(boardData)
    }

    // 게시판 데이터 수정
    fun editBoardData(position: Int, boardData: BoardDataClass) {
        // position이 유효한 인덱스 범위 내에 있으면 게시판 데이터를 수정
        if (position in boardDataList.indices) {
            boardDataList[position] = boardData
        }
    }

    // 게시판 데이터 목록 반환
    fun getBoardDataList(): List<BoardDataClass> {
        return boardDataList
    }
}