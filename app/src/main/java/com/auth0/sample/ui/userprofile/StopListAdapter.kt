package com.auth0.sample.ui.userprofile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.auth0.sample.databinding.StopItemLayoutBinding
import com.auth0.sample.domain.Stop

class StopListAdapter : ListAdapter<Stop, StopListAdapter.StopViewHolder>(DIFF_CALLBACK) {

    var onClick: ((Stop) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StopViewHolder {
        val binding = StopItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StopViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class StopViewHolder(
        private val binding: StopItemLayoutBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stop: Stop) {
            binding.apply {
                arrivalTime.text = stop.arrivalTime
                stopName.text = stop.name
                root.setOnClickListener { onClick?.invoke(stop) }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Stop>() {
            override fun areItemsTheSame(oldItem: Stop, newItem: Stop): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Stop, newItem: Stop): Boolean {
                return oldItem == newItem
            }

        }
    }
}
