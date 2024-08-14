package com.example.homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.homework.databinding.ActivityMainBinding

//Основная OnStart
//Основная OnResume
//Основная onPause
//Заполнение имени OnCreate
//Заполнение имени OnStart
//Заполнение имени OnResume
//Основная OnStop
//Заполнение имени onPause
//Основная OnStart
//Основная OnResume
//Заполнение имени OnStop
//Заполнение имени onDestroy
//Основная OnStart
//Основная OnResume
//Основная OnStart
//Основная OnResume
//Основная OnCreate
//Основная OnStart
//Основная OnResume
//Основная onPause
//Заполнение имени OnCreate
//Заполнение имени OnStart
//Заполнение имени OnResume
//Основная OnStop
//Заполнение имени onPause
//Основная OnStart
//Основная OnResume
//Заполнение имени OnStop
//Заполнение имени onDestroy
//Основная onPause
//Основная OnStop


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("ДЗ6", "Основная OnCreate")
        val launcher = registerForActivityResult(FillingActivity.GetFioContract()) {
            binding.setTextFio.text = it?.lastName.toString() + " " + it?.firstName + " " + it?.patronymic
        }

        binding.dialogButton.setOnClickListener {
            launcher.launch(binding.isFlagPatronymic.isChecked)
//            startActivity(this, binding.isFlagPatronymic.isChecked)
        }


    }
    override fun onStart() {
        super.onStart()
        Log.d("ДЗ6", "Основная OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ДЗ6", "Основная OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("ДЗ6", "Основная onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("ДЗ6", "Основная OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ДЗ6", "Основная onDestroy")
    }

    companion object {
        private const val isFlag = "isEnabled"
//        private fun startActivity(context: Context, isCheck: Boolean) {
//            val intent = Intent(context, FillingActivity::class.java)
//            intent.putExtra(isFlag, isCheck)
//            context.startActivity(intent)
//        }

    }
}


