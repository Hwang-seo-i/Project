package com.example.project.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.project.model.repository.PreferenceManager

class SettingViewModel(application: Application) : AndroidViewModel(application) {

    private val _carNumber = MutableLiveData<String>()
    val carNumber: LiveData<String> get() = _carNumber

    private val _automaticMap = MutableLiveData<Boolean>()
    val automaticMap: LiveData<Boolean> get() = _automaticMap

    private val _location = MutableLiveData<String>()
    val location: LiveData<String> get() = _location

    private val _mapInformation = MutableLiveData<Boolean>()
    val mapInformation: LiveData<Boolean> get() = _mapInformation

    private val _pathColor = MutableLiveData<String>()
    val pathColor: LiveData<String> get() = _pathColor

    init {
        val context = getApplication<Application>().applicationContext
        _carNumber.value = PreferenceManager.getString(context, "carNumber")
        _automaticMap.value = PreferenceManager.getBoolean(context, "automatic")
        _location.value = PreferenceManager.getString(context, "location")
        _mapInformation.value = PreferenceManager.getBoolean(context, "mapInformation")
        _pathColor.value = PreferenceManager.getString(context, "pathColor")
    }

    fun setCarNumber(carNumber: String) {
        val context = getApplication<Application>().applicationContext
        PreferenceManager.setString(context, "carNumber", carNumber)
        _carNumber.value = carNumber
    }

    fun setAutomaticMap(automatic: Boolean) {
        val context = getApplication<Application>().applicationContext
        PreferenceManager.setBoolean(context, "automatic", automatic)
        _automaticMap.value = automatic
    }

    fun setLocation(location: String) {
        val context = getApplication<Application>().applicationContext
        PreferenceManager.setString(context, "location", location)
        _location.value = location
    }

    fun setMapInformation(mapInformation: Boolean) {
        val context = getApplication<Application>().applicationContext
        PreferenceManager.setBoolean(context, "mapInformation", mapInformation)
        _mapInformation.value = mapInformation
    }

    fun setPathColor(pathColor: String) {
        val context = getApplication<Application>().applicationContext
        PreferenceManager.setString(context, "pathColor", pathColor)
        _pathColor.value = pathColor
    }
}
