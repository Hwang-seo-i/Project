// BoardRepository.kt
package com.example.project.model.repository

import com.example.project.model.data.BoardDataClass

class BoardRepository {
    private val boardDataList = mutableListOf<BoardDataClass>()

    fun addBoardData(boardData: BoardDataClass) {
        boardDataList.add(boardData)
    }

    fun editBoardData(position: Int, boardData: BoardDataClass) {
        if (position in boardDataList.indices) {
            boardDataList[position] = boardData
        }
    }

    fun getBoardDataList(): List<BoardDataClass> {
        return boardDataList
    }
}
