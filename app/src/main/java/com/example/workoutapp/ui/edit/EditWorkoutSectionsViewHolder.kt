package com.example.workoutapp.ui.edit

import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.SectionListItemBinding
import com.example.workoutapp.model.workout.WorkoutSection

class EditWorkoutSectionsViewHolder(
    val binding: SectionListItemBinding,
    val editWorkoutSectionsFragment: EditWorkoutSectionsFragment) : RecyclerView.ViewHolder(binding.root){
    init {
        binding.viewHolder = this
    }

    lateinit var section: WorkoutSection

    fun bind(section: WorkoutSection) {
        this.section = section
        binding.sectionNameText.text = section.name
    }

    fun sendSection() {
        editWorkoutSectionsFragment.addSectionToOrder(section.name)
    }

}