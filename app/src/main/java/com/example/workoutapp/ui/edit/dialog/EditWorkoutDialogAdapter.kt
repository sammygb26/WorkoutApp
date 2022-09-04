package com.example.workoutapp.ui.edit.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.SectionWorkoutDialogListItemBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.ui.edit.EditWorkoutFragment

class EditWorkoutDialogAdapter(val editViewModel: EditViewModel, val editWorkoutFragment: EditWorkoutFragment) : RecyclerView.Adapter<EditWorkoutDialogViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EditWorkoutDialogViewHolder {
        val binding = SectionWorkoutDialogListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return EditWorkoutDialogViewHolder(binding, editWorkoutFragment)
    }

    override fun onBindViewHolder(holder: EditWorkoutDialogViewHolder, position: Int) {
       holder.bind(editViewModel.getWorkoutSections()[position], position)
    }

    override fun getItemCount(): Int {
        return editViewModel.getWorkoutSections().size
    }
}