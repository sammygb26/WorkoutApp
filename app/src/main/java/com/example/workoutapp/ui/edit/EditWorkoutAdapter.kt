package com.example.workoutapp.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.SectionReferenceListItemBinding
import com.example.workoutapp.model.edit.EditViewModel

class EditWorkoutAdapter(
    private val sharedViewModel: EditViewModel
) : RecyclerView.Adapter<EditWorkoutViewHolder>(){

    override fun getItemCount(): Int {
        return sharedViewModel.workout!!.length
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EditWorkoutViewHolder {
        val binding = SectionReferenceListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return EditWorkoutViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EditWorkoutViewHolder, position: Int) {
        val section = sharedViewModel.workout!!.getSection(position)!!
        holder.bind(section)
    }
}