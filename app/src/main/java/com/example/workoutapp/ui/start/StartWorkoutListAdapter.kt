package com.example.workoutapp.ui.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.WorkoutListItemBinding
import com.example.workoutapp.model.start.StartViewModel

class StartWorkoutListAdapter(val viewModel: StartViewModel, val startFragment: StartFragment) : RecyclerView.Adapter<StartWorkoutListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): StartWorkoutListViewHolder {
        val binding = WorkoutListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = StartWorkoutListViewHolder(binding)

        holder.bindFragment(startFragment)
        holder.bindWorkout(viewModel.getWorkout(position)!!)
        return holder
    }

    override fun onBindViewHolder(holder: StartWorkoutListViewHolder, position: Int) {
        holder.bindWorkout(viewModel.getWorkout(position)!!)
    }

    override fun getItemCount(): Int = viewModel.getWorkoutsSize()
}