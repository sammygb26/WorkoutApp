package com.example.workoutapp.ui.edit

import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.SectionListItemBinding
import com.example.workoutapp.model.workout.WorkoutSection

class EditWorkoutSectionsViewHolder(val binding: SectionListItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(section: WorkoutSection) {
        binding.sectionNameText.text = section.name
    }
}