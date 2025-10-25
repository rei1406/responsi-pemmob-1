package com.example.responsi1mobileh1d023077.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.responsi1mobileh1d023077.data.model.SquadMember
import com.example.responsi1mobileh1d023077.databinding.ItemSquadMemberBinding

class SquadAdapter(
    private val squadMembers: List<SquadMember>,
    private val onItemClick: (SquadMember) -> Unit
) : RecyclerView.Adapter<SquadAdapter.SquadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadViewHolder {
        val binding = ItemSquadMemberBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SquadViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SquadViewHolder, position: Int) {
        holder.bind(squadMembers[position])
    }

    override fun getItemCount(): Int = squadMembers.size

    inner class SquadViewHolder(private val binding: ItemSquadMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(squadMember: SquadMember) {
            binding.tvName.text = squadMember.name
            binding.tvNationality.text = squadMember.nationality

            // Set background color based on position
            val backgroundColor = when {
                squadMember.position.contains("Goalkeeper", ignoreCase = true) -> 
                    Color.parseColor("#FFC107") // Yellow
                squadMember.position.contains("Back", ignoreCase = true) || 
                squadMember.position.contains("Centre-Back", ignoreCase = true) -> 
                    Color.parseColor("#2196F3") // Blue for Defenders
                squadMember.position.contains("Midfield", ignoreCase = true) || 
                squadMember.position.contains("Winger", ignoreCase = true) -> 
                    Color.parseColor("#4CAF50") // Green for Midfielders
                squadMember.position.contains("Forward", ignoreCase = true) || 
                squadMember.position.contains("Offence", ignoreCase = true) -> 
                    Color.parseColor("#F44336") // Red for Forwards
                else -> Color.parseColor("#9E9E9E") // Gray for unknown positions
            }
            
            binding.cardView.setCardBackgroundColor(backgroundColor)

            binding.root.setOnClickListener {
                onItemClick(squadMember)
            }
        }
    }
}