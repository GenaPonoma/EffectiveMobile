package com.example.effectivemobile.ui.authorization

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.effectivemobile.databinding.ActivityAuthorizationBinding

class AuthorizationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAuthorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}