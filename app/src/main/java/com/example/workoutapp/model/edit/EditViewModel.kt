package com.example.workoutapp.model.edit

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.workoutapp.data.WorkoutDataSource
import com.example.workoutapp.data.WorkoutFileSystemManager
import com.example.workoutapp.model.workout.Workout
import com.example.workoutapp.model.workout.WorkoutSection

class EditViewModel : ViewModel(){

    private var workoutFileSystemManager : WorkoutFileSystemManager? = null
    var workout : Workout? = null

    fun initializeEdit(context: Context, workoutName: String) {
        workoutFileSystemManager = WorkoutFileSystemManager(context)
        workout = workoutFileSystemManager!!.readWorkout(workoutName)
    }

    fun getWorkoutSections() = workout!!.sections

    fun saveWorkout() {
        workoutFileSystemManager!!.writeWorkout(workout!!)
    }
}