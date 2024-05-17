package com.example.project.model.data

import java.io.Serializable

data class BoardDataClass(
    val number: Int,
    val title: String,
    val date: String
) : Serializable