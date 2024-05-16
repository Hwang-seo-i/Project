package com.example.project.board

import java.io.Serializable

data class BoardDataClass(
    val number: Int,
    val title: String,
    val date: String
) : Serializable