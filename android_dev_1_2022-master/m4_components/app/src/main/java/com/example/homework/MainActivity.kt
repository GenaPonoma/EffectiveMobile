package com.example.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.homework.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateSaveButtonEnabled()
        returnProgress()

        binding.textInputEditName.addTextChangedListener {
            updateSaveButtonEnabled()
        }
        binding.textInputEditPhone.addTextChangedListener {
            updateSaveButtonEnabled()
        }
        binding.radioGroup.setOnCheckedChangeListener { _, _ ->
            updateSaveButtonEnabled()
        }
        binding.receiveUpdates.setOnCheckedChangeListener { _, isChecked  ->
            handleReceiveUpdates(isChecked)

        }
        binding.checkbox1.setOnCheckedChangeListener { _, _ ->
            updateSaveButtonEnabled()
        }
        binding.checkbox2.setOnCheckedChangeListener { _, _ ->
            updateSaveButtonEnabled()
        }
        binding.saveButton.setOnClickListener {
            Snackbar.make(it, R.string.text_notifications, Snackbar.LENGTH_LONG).show()
        }

    }
    private fun handleReceiveUpdates(isChecked: Boolean) {
        binding.checkbox1.isEnabled = isChecked
        binding.checkbox2.isEnabled = isChecked
    }
    private fun updateSaveButtonEnabled() {
        binding.saveButton.isEnabled = validateUserInput()
    }
    private fun validateUserInput(): Boolean {
        val checkboxSelected =
            binding.checkbox1.isChecked || binding.checkbox2.isChecked
        if (binding.textInputEditName.text?.isNotBlank() == true && binding.textInputEditName.length() <= 40) {
            if (binding.textInputEditPhone.text?.isNotBlank() == true) {
                if (binding.radioGroup.checkedRadioButtonId != -1) {
                    return if (binding.receiveUpdates.isChecked) {
                        checkboxSelected
                    } else {
                        true
                    }
                }
            }
        }
        return false
    }
    private fun returnProgress() {
        "${
            Random.nextInt(0, 101).also { binding.progressHorizontal.progress = it }
        }/100".also { binding.progressSet.text = it }
    }


}
