package com.example.workoutapp.ui.edit.dialog

import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.SectionWorkoutDialogListItemBinding
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutSectionType
import com.example.workoutapp.ui.edit.EditWorkoutFragment

class EditWorkoutDialogViewHolder(val binding: SectionWorkoutDialogListItemBinding, val editWorkoutFragment: EditWorkoutFragment) : RecyclerView.ViewHolder(binding.root) {
    fun bind(section: WorkoutSection, sectionIndex: Int) {
        binding.nameTextView.text = section.name

        binding.card.setOnClickListener {
            editWorkoutFragment.addSectionReference(sectionIndex)
        }

        binding.icon.setImageResource(
            when(section.type) {
                WorkoutSectionType.TIMER -> R.drawable.ic_timer
                WorkoutSectionType.CHECK -> R.drawable.ic_check_box
            }
        )

        binding.descriptionTextView.text = section.description

        binding.numberTextView.text = (when(section.type) {
            WorkoutSectionType.TIMER -> "%ss"
            WorkoutSectionType.CHECK -> "%sx"
        }).format(section.number)
    }
}