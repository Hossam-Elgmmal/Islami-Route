package com.route.islami.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.islami.databinding.ActivitySuraBinding
import com.route.islami.adapters.AyaAdapter
import com.route.islami.constants.Constants

class SuraActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuraBinding
    private lateinit var adapter: AyaAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backIcon.setOnClickListener {
            finish()
        }
        val title = intent.getStringExtra(Constants.EXTRA_NAME)
        val position = intent.getIntExtra(Constants.EXTRA_POSITION, -1)
        binding.nameOfSura.text = title
        adapter = AyaAdapter(null)

        readSura(position)
        binding.ayatRecycler.adapter = adapter
    }

    private fun readSura(index: Int) {

        val fileName = "$index.txt"
        val text = application.assets.open(fileName).bufferedReader().use {
            it.readText()
        }

        val ayatList = text.split("\n")
        adapter.updateData(ayatList)
    }
}