package com.example.workoutapp.ui.edit

import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.ui.reorder.ReorderHelperAdapter
import java.util.*

class EditWorkoutReorderHelperCallback(val viewModel: EditViewModel) : ReorderHelperAdapter {
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(viewModel.workout!!.sectionOrder, fromPosition, toPosition)
        return true
    }
}