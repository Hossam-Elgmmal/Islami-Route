package com.route.islami.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.R
import com.example.islami.databinding.ItemRadioBinding
import com.route.islami.adapters.model.Radio

class RadioAdapter : Adapter<RadioAdapter.RadioViewHolder>() {

    private val radiosList = mutableListOf<Radio>()
    var onPlayClick: (Radio, Int) -> Unit = { _, _ -> }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<Radio>) {
        radiosList.addAll(newList)
        notifyDataSetChanged()
    }

    fun updateRadio(isPlaying: Boolean, position: Int) {
        radiosList[position].isPlaying = isPlaying
        notifyItemChanged(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RadioViewHolder {
        return RadioViewHolder(
            ItemRadioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = radiosList.size

    override fun onBindViewHolder(holder: RadioViewHolder, position: Int) {
        val radio = radiosList[position]
        holder.bind(radio)
        holder.binding.playButton.setOnClickListener {
            onPlayClick(radio, position)

        }
    }

    inner class RadioViewHolder(val binding: ItemRadioBinding) : ViewHolder(binding.root) {
        fun bind(radio: Radio) {
            binding.apply {
                channelName.text = radio.name ?: ""
                if (radio.isPlaying)
                    playButton.setImageResource(R.drawable.ic_pause)
                else
                    playButton.setImageResource(R.drawable.ic_play)
            }
        }

    }
}