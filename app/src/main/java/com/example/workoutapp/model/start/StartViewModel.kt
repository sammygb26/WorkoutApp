package com.example.workoutapp.model.start

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.workoutapp.data.WorkoutDataSource
import com.example.workoutapp.data.WorkoutFileSystemManager
import com.example.workoutapp.model.workout.Workout

class StartViewModel : ViewModel(){
    var workoutNames = mutableListOf<String>()
    private val workouts = mutableMapOf<String, Workout>()

    fun getWorkoutsSize() = workoutNames.size

    fun getWorkout(name: String) : Workout? {
        if (workouts.containsKey(name))
            return workouts[name]
        return null
    }

    fun getWorkout(position: Int) : Workout? {
        if (position >= 0 && position < workoutNames.size) {
            val name = workoutNames[position]
            return getWorkout(name)
        }
        return null
    }

    fun loadWorkouts(context: Context) {
        val workoutFileSystemManager = WorkoutFileSystemManager(context)
        workoutNames = workoutFileSystemManager.workoutNames.toMutableList()

        workouts.clear()
        for (name in workoutNames) {
            val workout = workoutFileSystemManager.readWorkout(name)!!
            workouts[workout.name] = workout
        }

        if (workouts.isEmpty()) {
            workoutFileSystemManager.writeWorkout(
                WorkoutDataSource.basicWorkout,
            )
            loadWorkouts(context)
        }
    }
}