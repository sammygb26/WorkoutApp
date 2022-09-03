package com.example.workoutapp.ui.edit

import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.SectionReferenceListItemBinding
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutSectionType

class EditWorkoutViewHolder(val binding: SectionReferenceListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(section: WorkoutSection) {
        binding.nameTextView.text = section.name

        binding.icon.setImageResource(
            when(section.type) {
                WorkoutSectionType.TIMER -> R.drawable.ic_timer
                WorkoutSectionType.CHECK -> R.drawable.ic_check_box
            }
        )

        binding.numberTextView.text = (when(section.type) {
            WorkoutSectionType.TIMER -> "%ss"
            WorkoutSectionType.CHECK -> "%sx"
        }).format(section.number)
    }
}