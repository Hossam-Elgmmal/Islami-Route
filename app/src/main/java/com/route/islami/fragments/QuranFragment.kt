package com.route.islami.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentQuranBinding
import com.route.islami.activities.SuraActivity
import com.route.islami.adapters.SuraNameAdapter
import com.route.islami.adapters.model.SuraItem
import com.route.islami.adapters.model.nameList
import com.route.islami.constants.Constants

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
        adapter.listener = object : SuraNameAdapter.onSuraClickListener {
            override fun onClick(name: String, position: Int) {

                val intent = Intent(requireContext(), SuraActivity::class.java)
                intent.putExtra(Constants.EXTRA_NAME, name)
                intent.putExtra(Constants.EXTRA_POSITION, position)
                startActivity(intent)
            }

        }
        binding.suraRecycler.adapter = adapter

    }
}