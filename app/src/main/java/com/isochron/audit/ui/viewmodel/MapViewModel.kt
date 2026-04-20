package com.isochron.audit.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.isochron.audit.data.repository.DeviceRepository

class MapViewModel(app: Application) : AndroidViewModel(app) {
    val repository = DeviceRepository(app)
    var selectedBssid by mutableStateOf<String?>(null)
}
