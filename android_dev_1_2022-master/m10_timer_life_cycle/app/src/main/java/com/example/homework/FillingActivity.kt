package com.example.homework

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View.*
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import com.example.homework.databinding.ActivityFillingBinding

class FillingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFillingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("ДЗ6", "Заполнение имени OnCreate")

        launchIntent = intent
        isVisible()
        var savedTextFirstName = savedInstanceState?.getString(SAVED_TEXT_FIRST_NAME) ?: ""
        var savedTextLastName = savedInstanceState?.getString(SAVED_TEXT_LAST_NAME) ?: ""
        var savedTextPatronymic = savedInstanceState?.getString(SAVED_TEXT_PATRONYMIC) ?: ""
        binding.inputTextEditFirstname.setText(savedTextFirstName)
        binding.inputTextEditLastname.setText(savedTextLastName)
        binding.inputTextEditPatronymic.setText(savedTextPatronymic)
        binding.saveButton.setOnClickListener {
            val user = User(
                firstName = binding.inputTextEditFirstname.text.toString(),
                lastName = binding.inputTextEditLastname.text.toString(),
                patronymic = binding.inputTextEditPatronymic.text.toString()
            )

            val date = Intent()
            date.putExtra(EXTRA_USER, user)
            setResult(Activity.RESULT_OK, date)
            finish()
        }

    }

    override fun onStart() {
        super.onStart()
        Log.d("ДЗ6", "Заполнение имени OnStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("ДЗ6", "Заполнение имени OnResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("ДЗ6", "Заполнение имени onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("ДЗ6", "Заполнение имени OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("ДЗ6", "Заполнение имени onDestroy")
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SAVED_TEXT_FIRST_NAME, binding.inputTextEditFirstname.text.toString())
        outState.putString(SAVED_TEXT_LAST_NAME, binding.inputTextEditLastname.text.toString())
        outState.putString(SAVED_TEXT_PATRONYMIC, binding.inputTextEditPatronymic.text.toString())
    }

    private fun isVisible() {
        val visibleInputText = launchIntent.getBooleanExtra(EXTRA_USER, false)
        if (visibleInputText) binding.inputTextPatronymic.visibility = VISIBLE else INVISIBLE
    }

    class GetFioContract : ActivityResultContract<Boolean, User?>() {

        override fun createIntent(context: Context, input: Boolean): Intent {
            return Intent(context, FillingActivity::class.java).apply {
                putExtra(EXTRA_USER_DATA, input)
            }
        }

        override fun parseResult(resultCode: Int, intent: Intent?): User? {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    val user: User? = intent?.let {
                        IntentCompat.getParcelableExtra(it, EXTRA_USER_DATA, User::class.java)
                    } ?: return null
                    return user
                }

                AUTH_FAILED -> return null
                else -> return null

            }
        }

        companion object {
            private const val EXTRA_USER_DATA = "isEnabled"
            private const val AUTH_FAILED = 10
        }

    }

    companion object {
        const val SAVED_TEXT_FIRST_NAME = "savedTextFirstName"
        const val SAVED_TEXT_LAST_NAME = "savedTextLastName"
        const val SAVED_TEXT_PATRONYMIC = "savedTextPatronymic"
        private lateinit var launchIntent: Intent
        private lateinit var binding: ActivityFillingBinding
        private const val EXTRA_USER = "isEnabled"

    }
}


