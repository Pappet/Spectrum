package com.isochron.audit.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.isochron.audit.data.repository.DeviceRepository
import com.isochron.audit.util.LanDevice
import com.isochron.audit.util.LanScanProgress
import com.isochron.audit.util.NetworkDiscovery
import com.isochron.audit.util.NetworkInfo
import com.isochron.audit.util.PingUtil
import com.isochron.audit.util.PortScanProgress
import com.isochron.audit.util.PortScanResult
import com.isochron.audit.util.PortScanner
import kotlinx.coroutines.launch

class LanViewModel(app: Application) : AndroidViewModel(app) {

    val discovery = NetworkDiscovery(app)
    val pingUtil = PingUtil(app)
    val portScanner = PortScanner()
    val repository = DeviceRepository(app)

    var devices by mutableStateOf<List<LanDevice>>(emptyList())
        private set
    var isScanning by mutableStateOf(false)
        private set
    var hasScanned by mutableStateOf(false)
        private set
    var progress by mutableStateOf<LanScanProgress?>(null)
        private set
    var networkInfo by mutableStateOf<NetworkInfo?>(null)
        private set
    var portScanResults by mutableStateOf<Map<String, List<PortScanResult>>>(emptyMap())
        private set
    var portScanningIp by mutableStateOf<String?>(null)
        private set
    var portScanProgress by mutableStateOf<PortScanProgress?>(null)
        private set

    fun scan() {
        if (isScanning) return
        isScanning = true
        try { networkInfo = pingUtil.getNetworkInfo() } catch (e: Exception) {
            android.util.Log.e("LanViewModel", "Error getting network info", e)
        }
        viewModelScope.launch {
            try {
                val result = discovery.fullScan(
                    onProgress = { progress = it },
                    onDeviceFound = { devices = it },
                )
                devices = result
                try { repository.persistLanScan(result) } catch (e: Exception) {
                    android.util.Log.e("LanViewModel", "Error persisting LAN scan", e)
                }
            } catch (e: Exception) {
                android.util.Log.e("LanViewModel", "Error in LAN scan", e)
            } finally {
                isScanning = false
                hasScanned = true
                progress = null
            }
        }
    }

    fun startPortScan(ip: String, ports: List<Int>) {
        portScanningIp = ip
        viewModelScope.launch {
            try {
                val results = portScanner.scan(
                    ip = ip,
                    ports = ports,
                    grabBanners = true,
                    onProgress = { portScanProgress = it },
                )
                portScanResults = portScanResults + (ip to results)
                try { repository.persistPortScanResults(ip, results) } catch (_: Exception) {}
            } catch (e: Exception) {
                android.util.Log.e("LanViewModel", "Port scan error", e)
            }
            portScanningIp = null
            portScanProgress = null
        }
    }

    fun toggleFavorite(address: String) {
        viewModelScope.launch { repository.toggleFavoriteByAddress(address) }
    }

    override fun onCleared() {
        super.onCleared()
        discovery.stopScan()
    }
}
