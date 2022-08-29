package com.example.workoutapp.ui.reorder

interface ReorderHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int) : Boolean
}