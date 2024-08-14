package com.example.gamebatll

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.gamebatll.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val REQUEST_CODE_BLUETOOTH = 100
    private val launcher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Разрешения получены!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Разрешение не получено!", Toast.LENGTH_SHORT).show()
            }
        }


    private fun requestBluetoothPermission() {
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                // Разрешение предоставлено, можно использовать Bluetooth
                Toast.makeText(this, "Разрешение на использование Bluetooth получено.", Toast.LENGTH_SHORT).show()
            } else {
                // Пользователь не предоставил разрешение, обработать ошибку
                Toast.makeText(this, "Разрешение на использование Bluetooth не предоставлено.", Toast.LENGTH_SHORT).show()
            }
        }.launch(Manifest.permission.BLUETOOTH)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (checkSelfPermission(Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            requestBluetoothPermission()
        } else {
            requestBluetoothPermission()
        }

    }

    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(this, "Разрешения не получены! ", Toast.LENGTH_SHORT).show()
        } else {
            launcher.launch(Manifest.permission.BLUETOOTH)
        }
    }


}