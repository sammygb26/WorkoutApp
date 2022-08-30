package com.example.workoutapp.ui.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.SectionListItemBinding
import com.example.workoutapp.model.edit.EditViewModel

class EditWorkoutSectionsAdapter(
    private val sharedViewModel: EditViewModel,
    private val editWorkoutSectionsFragment: EditWorkoutSectionsFragment
    ) : RecyclerView.Adapter<EditWorkoutSectionsViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EditWorkoutSectionsViewHolder {
        val binding = SectionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return EditWorkoutSectionsViewHolder(binding, editWorkoutSectionsFragment)
    }

    override fun onBindViewHolder(holder: EditWorkoutSectionsViewHolder, position: Int) {
        val section = sharedViewModel.workout!!.sections[position]
        holder.bind(section)
    }

    override fun getItemCount(): Int {
        return sharedViewModel.workout!!.sections.size
    }
}