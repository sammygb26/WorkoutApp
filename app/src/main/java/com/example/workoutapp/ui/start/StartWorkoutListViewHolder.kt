package com.example.workoutapp.ui.start

import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.WorkoutListItemBinding
import com.example.workoutapp.model.workout.Workout

class StartWorkoutListViewHolder(val binding: WorkoutListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindWorkout(workout: Workout) {
        binding.workoutNameText.text = workout.name
        binding.name = workout.name
        binding.workoutDescriptionText.text = workout.description
    }

    fun bindFragment(startFragment: StartFragment) {
        binding.startFragment = startFragment
    }
}