package com.example.aisleassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aisleassignment.databinding.LikesRowBinding
import com.example.aisleassignment.model.notes.Profiles

class LikesAdapter(
    val context: Context,
    private var profiles: MutableList<Profiles>
) : RecyclerView.Adapter<LikesAdapter.ViewHolder>() {

    private lateinit var dataBinding: LikesRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        dataBinding =
            LikesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val profile = profiles[position]
        holder.dataBinding.profile = profile
    }

    override fun getItemCount(): Int {
        return profiles.size
    }

    class ViewHolder(
        val dataBinding: LikesRowBinding
    ) : RecyclerView.ViewHolder(dataBinding.root)
}