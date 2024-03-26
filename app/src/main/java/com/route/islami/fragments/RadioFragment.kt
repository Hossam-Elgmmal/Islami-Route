package com.route.islami.fragments

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.islami.databinding.FragmentRadioBinding
import com.google.android.material.snackbar.Snackbar
import com.route.islami.adapters.RadioAdapter
import com.route.islami.adapters.model.RadiosResponse
import com.route.islami.api.ApiManager
import com.route.islami.services.RadioPlayerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RadioFragment : Fragment() {
    private lateinit var binding: FragmentRadioBinding
    private val adapter = RadioAdapter()
    var radioService: RadioPlayerService? = null
    var bound = false
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
        adapter.onPlayClick = { radio, position ->

            radioService?.let { radioService ->
                if (!radioService.getIsPlaying()) {
                    radioService
                        .startMediaPlayer(
                            radio.url ?: "",
                            radio.name ?: "",
                            radio.id ?: 0
                        )
                    adapter.updateRadio(true, position)
                } else {
                    radioService.pauseMediaPlayer()
                    adapter.updateRadio(false, position)
                }
            }

        }
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
                                response.message() ?: "no response",
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

    override fun onStart() {
        super.onStart()
        radioService?.stopSelf()
        startService()
        bindService()

    }

    private fun startService() {
        val intent = Intent(requireActivity(), RadioPlayerService::class.java)
        requireActivity().startService(intent)

    }


    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {


            val binder = service as RadioPlayerService.MyBinder
            radioService = binder.getService()
            bound = true


        }

        override fun onServiceDisconnected(name: ComponentName?) {

            bound = false
            Log.e("FATAL", "onServiceDisconnected: $name ")

        }

    }

    private fun bindService() {
        val intent = Intent(requireContext(), RadioPlayerService::class.java)
        requireActivity().bindService(intent, connection, Context.BIND_AUTO_CREATE)

    }

    override fun onStop() {
        super.onStop()
        requireActivity().unbindService(connection)

    }

}