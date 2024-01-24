package com.route.islami.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.islami.databinding.FragmentQuranBinding
import com.route.islami.adapters.SuraNameAdapter
import com.route.islami.adapters.model.SuraItem
import com.route.islami.adapters.model.nameList

class QuranFragment : Fragment() {

    private lateinit var binding: FragmentQuranBinding
    private lateinit var adapter: SuraNameAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentQuranBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val suraList = nameList.mapIndexed { index, name ->
            SuraItem(name, index + 1)
        }

        adapter = SuraNameAdapter(suraList)

        binding.suraRecycler.adapter = adapter

    }
}