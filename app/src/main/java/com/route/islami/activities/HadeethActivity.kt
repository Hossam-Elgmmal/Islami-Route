package com.route.islami.activities

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.islami.databinding.ActivityHadeethBinding
import com.route.islami.constants.Constants

class HadeethActivity : AppCompatActivity() {
    lateinit var binding: ActivityHadeethBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHadeethBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backIcon.setOnClickListener {
            finish()
        }
        val title = intent.getStringExtra(Constants.EXTRA_NAME)
        val details = intent.getStringExtra(Constants.EXTRA_DETAILS)

        binding.nameOfHadeeth.text = title
        binding.hadeethDetail.text = details
        binding.hadeethDetail.movementMethod = ScrollingMovementMethod()

    }
}