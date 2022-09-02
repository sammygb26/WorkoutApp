package com.example.workoutapp.model.start

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.workoutapp.data.WorkoutDataSource
import com.example.workoutapp.data.WorkoutFileSystemManager
import com.example.workoutapp.model.workout.Workout
import com.example.workoutapp.model.workout.WorkoutSection

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

    fun addWorkout(workout: Workout, context: Context) {
        val workoutFileSystemManager = WorkoutFileSystemManager(context)
        workoutFileSystemManager.writeWorkout(workout)

        loadWorkouts(context)
    }

    fun loadWorkouts(context: Context) {
        val workoutFileSystemManager = WorkoutFileSystemManager(context)
        workoutFileSystemManager.writeWorkout(
            WorkoutDataSource.basicWorkout
        )
        workoutNames = workoutFileSystemManager.workoutNames.toMutableList()

        workouts.clear()
        for (name in workoutNames) {
            val workout = workoutFileSystemManager.readWorkout(name)!!
            workouts[workout.name] = workout
        }

        if (workouts.isEmpty()) {
            workoutFileSystemManager.writeWorkout(
                WorkoutDataSource.basicWorkout
            )
            loadWorkouts(context)
        }
    }
}