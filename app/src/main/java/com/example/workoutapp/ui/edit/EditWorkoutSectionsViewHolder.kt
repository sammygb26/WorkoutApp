package com.example.workoutapp.ui.edit

import android.app.AlertDialog
import android.content.DialogInterface
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.SectionListItemBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutSectionType

class EditWorkoutSectionsViewHolder(
    val binding: SectionListItemBinding,
    private val editWorkoutSectionsFragment: EditWorkoutSectionsFragment, ) : RecyclerView.ViewHolder(binding.root){
    init {
        binding.viewHolder = this
    }

    lateinit var section: WorkoutSection

    fun bind(section: WorkoutSection) {
        this.section = section
        binding.sectionTextView.text = section.name

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

    fun sendSection() {
        editWorkoutSectionsFragment.addSectionToOrder(section.name)
    }

    fun deleteRequest() {
        val context = binding.root.context
        val alertDialogBuilder = AlertDialog.Builder(context)

        val positiveClick = { _: DialogInterface, _: Int ->
            delete()
        }

        alertDialogBuilder.setMessage(context.getString(R.string.delete_section_check_message))
        alertDialogBuilder.setPositiveButton(context.getString(R.string.yes), positiveClick)
        alertDialogBuilder.show()
    }

    private fun delete() {
        editWorkoutSectionsFragment.removeSection(section)
    }

}