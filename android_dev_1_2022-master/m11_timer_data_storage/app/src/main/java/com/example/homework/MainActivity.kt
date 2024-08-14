package com.example.homework

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val repository = Repository(applicationContext)
        binding.textResult.text = repository.getText()




        binding.saveButton.setOnClickListener {

            repository.saveText(binding.editText.text.toString())
            binding.textResult.text = repository.getText()
            Toast.makeText(this, getString(R.string.save_toast), Toast.LENGTH_SHORT).show()
        }
        binding.clearButton.setOnClickListener {
            repository.clearText()
            binding.textResult.text = repository.getText()
            Toast.makeText(this, getString(R.string.clear_toast), Toast.LENGTH_SHORT).show()
        }
    }
}