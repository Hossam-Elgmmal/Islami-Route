package com.route.islami.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.databinding.ItemAyaBinding

class AyaAdapter(private var ayaList: List<String>?) : Adapter<AyaAdapter.AyaViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AyaViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemAyaBinding.inflate(inflater, parent, false)
        return AyaViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return ayaList?.size ?: 0
    }

    override fun onBindViewHolder(holder: AyaViewHolder, position: Int) {
        val item = ayaList?.get(position) ?: return
        holder.bind(item)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(ayatList: List<String>) {
        this.ayaList = ayatList
        notifyDataSetChanged()
    }

    class AyaViewHolder(val binding: ItemAyaBinding) : ViewHolder(binding.root) {

        fun bind(aya: String) {

            binding.aya.text = aya

        }
    }
}