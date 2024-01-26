package com.route.islami.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.R
import com.example.islami.databinding.FragmentSebhaBinding

class SebhaFragment : Fragment() {
    private lateinit var binding: FragmentSebhaBinding
    private var count = 0
    private lateinit var azkarList: MutableList<String>
    private var position = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSebhaBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        azkarList = resources.getStringArray(R.array.azkar).toMutableList()

        binding.currentValue.setOnClickListener {
            count = 0
            binding.currentValue.text = "$count"
            binding.sebhaBody.rotation = 0F
        }

        binding.countUp.setOnClickListener {
            count()
        }
        binding.sebhaBody.setOnClickListener {
            count()
        }
    }

    private fun count() {
        count++
        if (count >= 33) {
            count = 0
            position++
            if (position >= azkarList.size) {
                position = 0
            }
            binding.countUp.text = azkarList[position]
            binding.sebhaBody.rotation = 0F
        }
        binding.currentValue.text = "$count"
        binding.sebhaBody.rotation += (360 / 33).toFloat()

    }
}