package com.route.islami.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentRadioBinding
import com.google.android.material.snackbar.Snackbar
import com.route.islami.adapters.RadioAdapter
import com.route.islami.adapters.model.RadiosResponse
import com.route.islami.api.ApiManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioFragment : Fragment() {
    private lateinit var binding: FragmentRadioBinding
    private val adapter = RadioAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRadioBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        getRadiosFromApi()
    }

    private fun initRecycler() {
        binding.radioRecycler.adapter = adapter
    }

    private fun getRadiosFromApi() {
        ApiManager.radioService.getRadios()
            .enqueue(object : Callback<RadiosResponse> {
                override fun onResponse(
                    call: Call<RadiosResponse>,
                    response: Response<RadiosResponse>
                ) {
                    if (response.isSuccessful) {

                        val list = response.body()?.radios ?: emptyList()
                        adapter.setList(list)
                    } else {
                        Snackbar
                            .make(
                                binding.root,
                                "no response",
                                Snackbar.LENGTH_LONG
                            )
                            .show()
                    }
                }

                override fun onFailure(call: Call<RadiosResponse>, t: Throwable) {
                    Snackbar
                        .make(
                            binding.root,
                            t.message ?: "failed response",
                            Snackbar.LENGTH_LONG
                        )
                        .show()
                }

            })
    }
}