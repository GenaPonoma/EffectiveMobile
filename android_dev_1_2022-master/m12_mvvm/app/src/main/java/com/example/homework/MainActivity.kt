package com.example.homework

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    private val viewModel: MainViewModel by viewModels { MainViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonSearch.setOnClickListener {
            viewModel.isActiveButton(binding.searchInputField.text.toString())
        }

        binding.searchInputField.doOnTextChanged { text, _, _, _ ->
            viewModel.validateField(text.toString())
        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.message.collect { message ->
                    binding.resultText.text = message
                }
            }


        }
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.state.collect { state ->
                    when (state) {
                        State.Loading -> {
                            binding.buttonSearch.isEnabled = false
                            binding.resultText.visibility = View.INVISIBLE
                            binding.progress.visibility = View.VISIBLE
                        }

                        State.Success -> {
                            binding.buttonSearch.isEnabled = true
                            binding.resultText.visibility = View.VISIBLE
                            binding.progress.visibility = View.INVISIBLE
                        }

                        State.ButtonNotActive -> {
                            binding.progress.visibility = View.INVISIBLE
                            binding.buttonSearch.isEnabled = false
                        }

                        State.ButtonActive -> {
                            binding.buttonSearch.isEnabled = true
                        }

                    }
                }
            }
        }
    }
}















