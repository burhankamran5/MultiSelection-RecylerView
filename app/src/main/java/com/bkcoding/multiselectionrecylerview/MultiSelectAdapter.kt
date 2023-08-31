package com.bkcoding.multiselectionrecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bkcoding.multiselectionrecylerview.MainActivity.DataSet
import com.bkcoding.multiselectionrecylerview.databinding.ItemDesignBinding

class MultiSelectAdapter(
    val data: MutableList<DataSet>,
    private val showMenuDelete: (Boolean) -> Unit,
) :
    RecyclerView.Adapter<MultiSelectAdapter.MultiSelectViewHolder>() {

    private var isEnable = false
    private val itemSelectedList = mutableListOf<Int>()

    inner class MultiSelectViewHolder(private val binding: ItemDesignBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tv = binding.number
        val image = binding.tick
        fun bind(data: DataSet) {
            binding.tick.visibility = View.GONE
            binding.number.text = data.number.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiSelectViewHolder {
        val binding = ItemDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MultiSelectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MultiSelectViewHolder, position: Int) {
        holder.bind(data[position])
        val item = data[position]
        Log.d("onBindViewHolderItem:", ": ${data[position]}")
        Log.d("XData:", ": ${holder.bind(data[position])}")
        holder.tv.setOnLongClickListener {
            selectItem(holder, item, position)
            true
        }

        if (itemSelectedList.contains(position)) {
            selectItem(holder, item, position)
        }

        holder.tv.setOnClickListener {
            if (itemSelectedList.contains(position)) {
                itemSelectedList.remove(position)
                holder.image.visibility = View.GONE
                item.isSelected = false


                if (itemSelectedList.isEmpty()) {
                    showMenuDelete(false)
                    isEnable = false
                }
            } else if (isEnable) {
                selectItem(holder, item, position)
            }


        }
    }

    private fun selectItem(
        holder: MultiSelectAdapter.MultiSelectViewHolder,
        item: DataSet,
        position: Int
    ) {
        isEnable = true
        itemSelectedList.add(position)
        item.isSelected = true
        holder.image.visibility = View.VISIBLE
        showMenuDelete(true)


    }


    fun deleteSelectedItem() {
        if (itemSelectedList.isNotEmpty()) {
            data.removeAll { item -> item.isSelected }
            isEnable = false
            itemSelectedList.clear()
        }
        notifyDataSetChanged()
    }

}
