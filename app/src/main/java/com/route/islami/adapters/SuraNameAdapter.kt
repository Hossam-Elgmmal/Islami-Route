package com.route.islami.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.islami.databinding.ItemSuraBinding
import com.route.islami.adapters.model.SuraItem

class SuraNameAdapter(private val itemList: List<SuraItem>?) : Adapter<SuraNameAdapter.SuraItemViewHolder>() {

    lateinit var listener: onSuraClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuraItemViewHolder {

        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemSuraBinding.inflate(inflater, parent, false)
        return SuraItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SuraItemViewHolder, position: Int) {
        val item = itemList?.get(position) ?: return

        holder.binding.root.setOnClickListener {
            listener.onClick(item.name, item.index)
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int = itemList?.size ?: 0

    class SuraItemViewHolder(val binding: ItemSuraBinding) : ViewHolder(binding.root) {

        fun bind(item: SuraItem) {

            binding.suraName.text = item.name
            binding.suraIndex.text = "${item.index}"
        }
    }

    interface onSuraClickListener {
        fun onClick(name: String, position: Int)
    }

}