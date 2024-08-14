package com.example.gamebatll

import android.Manifest
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Intent
import android.content.pm.PackageManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.ActivityCompat
import java.io.IOException
import java.util.UUID


class BluetoothService : Service(), BluetoothServerSocketCallback {

    private lateinit var mBluetoothAdapter: BluetoothAdapter
    private var mServerSocket: BluetoothServerSocket? = null

    override fun onBind(intent: Intent): IBinder? {
        // Возвращаем null, так как сервис не предназначен для прямого вызова
        return null
    }


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        Log.i(TAG, "onStartCommand")

        // Инициализация Bluetooth
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (mBluetoothAdapter == null) {
            Log.w(TAG, "Bluetooth недоступен.")
            stopSelf()
            return START_NOT_STICKY
        }

        // Создание серверного сокета
        try {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.BLUETOOTH_CONNECT
                ) != PackageManager.PERMISSION_GRANTED
            ) {

            }
            mServerSocket =
                mBluetoothAdapter.listenUsingRfcommWithServiceRecord("MyService", MY_UUID)
        } catch (e: IOException) {
            e.printStackTrace()
            stopSelf()
        }

        return START_STICKY
    }

    override fun onDestroy() {
        Log.i(TAG, "onDestroy")
        if (mServerSocket != null) {
            try {
                mServerSocket?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    override fun onAccept(socket: BluetoothSocket) {
        Log.i(TAG, "onAccept: Соединение принято.")
        // Здесь вы можете обработать принятое соединение
    }

    override fun onReject(device: String, errorCode: Int) {
        Log.w(TAG, "onReject: Соединение отклонено.")
        // Обработка отклоненного соединения
    }

    companion object {
        private const val TAG = "BluetoothService"

        // Замените MY_UUID на UUID вашего сервиса
        private val MY_UUID = UUID.fromString("123e4567-e89b-12d3-a456-426655440000")
    }
}

interface BluetoothServerSocketCallback {


    fun onReject(device: String, errorCode: Int)
    fun onAccept(socket: BluetoothSocket)
}