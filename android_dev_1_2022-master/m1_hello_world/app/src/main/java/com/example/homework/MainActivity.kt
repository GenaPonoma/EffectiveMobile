package com.example.homework

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.homework.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        updateScreenState()

        binding.plusButton.setOnClickListener {
            counter++
            updateScreenState()

        }
        binding.minusButton.setOnClickListener {
            counter--

            updateScreenState()

        }
        binding.resetButton.setOnClickListener {
            counter = 0
            updateScreenState()
        }

    }


    private fun updateScreenState() {
        when (counter) {
            0 -> {
                outputCounterZero()
            }

            in 1..49 -> {
                countFreePlaces = calcPlaces()
                outputCounterRange()

            }

            50 -> {
                outputCounterReset()
            }


        }
    }

    private fun outputCounterZero() {
        binding.resetButton.visibility = View.INVISIBLE
        binding.informationMessage.text = "Все места свободны"
        binding.informationMessage.setTextColor(Color.parseColor("#0aad3f"))
        binding.placeCounter.text = counter.toString()
    }

    @SuppressLint("SetTextI18n")
    fun outputCounterRange() {
        binding.placeCounter.text = counter.toString()
        binding.resetButton.visibility = View.INVISIBLE
        binding.informationMessage.setTextColor(Color.parseColor("#0000ff"))
        binding.informationMessage.text = "Колличество свободных мест: $countFreePlaces"
    }

    private fun outputCounterReset() {
        binding.placeCounter.text = counter.toString()
        binding.informationMessage.text = "Пассажиров слишком много !"
        binding.informationMessage.setTextColor(Color.parseColor("#FF0000"))
        binding.resetButton.visibility = View.VISIBLE
    }

    private fun calcPlaces(): Int {
        return remainingSeats - counter

    }

    companion object {
        private const val remainingSeats = 50
        private var counter = 0
        private var countFreePlaces = remainingSeats
        private lateinit var binding: ActivityMainBinding

    }

}