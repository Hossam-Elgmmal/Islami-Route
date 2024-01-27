package com.route.islami.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentHadeethBinding
import com.route.islami.adapters.AyaAdapter

class HadeethFragment : Fragment() {
    private lateinit var binding: FragmentHadeethBinding
    private lateinit var adapter: AyaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHadeethBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AyaAdapter(null)
        readHadeethName()
        binding.hadeethName.adapter = adapter

    }

    private fun readHadeethName() {

        val fileName = "ahadeeth.txt"
        val ahadeeth = requireContext().assets.open(fileName).bufferedReader().use {
            it.readText()
        }
        val ahadeethList = ahadeeth.trim().split("#")

        val ahadeethNameList = mutableListOf<String>()
        val ahadeethDetailList = mutableListOf<String>()

        ahadeethList.forEach { hadeeth ->
            val lines = hadeeth.trim().split("\n").toMutableList()
            ahadeethNameList.add(lines[0])
            lines.removeAt(0)
            ahadeethDetailList.add(lines.joinToString("\n"))

        }
        adapter.updateData(ahadeethNameList)
    }
}