package com.example.workoutapp.ui.edit

import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.ui.reorder.ReorderHelperAdapter
import java.util.*

class EditWorkoutReorderHelperCallback(val viewModel: EditViewModel, val recyclerViewAdapter: RecyclerView.Adapter<*>) : ReorderHelperAdapter {
    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(viewModel.workout!!.sectionOrder, fromPosition, toPosition)
        return true
    }

    override fun onItemSwipe(itemPosition: Int, direction: Int) {
        viewModel.removeReference(itemPosition)
        recyclerViewAdapter.notifyItemRemoved(itemPosition)
    }
}