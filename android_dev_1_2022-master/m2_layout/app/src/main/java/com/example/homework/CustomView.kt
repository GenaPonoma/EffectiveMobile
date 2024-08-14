package com.example.homework

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.homework.databinding.CustomViewBinding

class CustomView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :


    LinearLayout(context, attrs) {
    private  var  binding: CustomViewBinding

    init {
        val inflatedView = inflate(context, R.layout.custom_view, this)
        binding = CustomViewBinding.bind(inflatedView)
    }


    fun setMessageUpText(message: String) {
        binding.upLineText.text = message
    }

    fun setMessageDownText(message: String) {
        binding.downLineText.text = message
    }


}
