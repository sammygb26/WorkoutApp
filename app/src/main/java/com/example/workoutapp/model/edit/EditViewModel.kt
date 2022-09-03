package com.example.workoutapp.model.edit

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.workoutapp.data.WorkoutDataSource
import com.example.workoutapp.data.WorkoutFileSystemManager
import com.example.workoutapp.model.workout.Workout
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutSectionType

class EditViewModel : ViewModel(){

    private var workoutFileSystemManager : WorkoutFileSystemManager? = null
    var workout : Workout?  = null
    var section : WorkoutSection? = null

    fun initializeWorkoutEdit(context: Context, workoutName: String) {
        workoutFileSystemManager = WorkoutFileSystemManager(context)
        workout = workoutFileSystemManager!!.readWorkout(workoutName)
    }

    fun initializeSectionEdit(section: WorkoutSection) {
        this.section = section
    }

    fun appendSectionToOrder(sectionName: String) {
        for (i in workout!!.sections.indices) {
            if (workout!!.sections[i].name == sectionName) {
                workout!!.sectionOrder.add(i)
            }
        }
    }

    fun appendCurrentSection() : Boolean {
        if (section != null && workout != null) {
            workout!!.sections.add(section!!)
            return true
        }
        return false
    }

    fun setSectionName(name: String) {
        section!!.name = name
    }

    fun setSectionDescription(description: String) {
        section!!.description = description
    }

    fun setSectionNumber(number: Int) {
        section!!.number = number
    }

    fun setSectionType(type: WorkoutSectionType) {
        section!!.type = type
    }

    fun setFormatString(formatString: String) {
        section!!.formatString = formatString
    }

    fun getWorkoutSections() = workout!!.sections

    fun saveWorkout() {
        workoutFileSystemManager!!.writeWorkout(workout!!)
    }

    fun removeReference(position: Int) {
        workout!!.sectionOrder.removeAt(position)
    }

    fun removeSection(section: WorkoutSection): Int {
        val position = workout!!.sections.indexOf(section)
        workout!!.sections.removeAt(position)
        workout!!.sectionOrder = workout!!.sectionOrder.filter {
            it!=position
        }.map {
            if (it < position) it else it - 1
        } as MutableList<Int>
        return position
    }
}