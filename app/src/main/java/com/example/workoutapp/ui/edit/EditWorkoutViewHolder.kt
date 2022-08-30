package com.example.workoutapp.ui.edit

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.SectionReferenceListItemBinding
import com.example.workoutapp.model.workout.WorkoutSection

class EditWorkoutViewHolder(val binding: SectionReferenceListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(workoutSection: WorkoutSection) {
        binding.sectionNameText.text = workoutSection.name
    }
}