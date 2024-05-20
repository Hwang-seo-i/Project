package com.example.project.model

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.project.model.repository.PreferenceManager

class LocationSelectViewModel : ViewModel() {

    // 저장된 위치를 가져오는 메소드
    fun getSavedLocation(context: Context): String {
        return PreferenceManager.getString(context, "location") ?: "왼쪽"
    }

    // 선택된 위치를 저장하는 메소드
    fun saveLocation(context: Context, location: String) {
        PreferenceManager.setString(context, "location", location)
    }
}