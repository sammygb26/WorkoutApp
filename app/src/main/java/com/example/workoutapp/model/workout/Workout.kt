package com.example.workoutapp.model.workout

data class Workout(
    var name: String,
    var sections: MutableList<WorkoutSection> = mutableListOf(),
    var sectionOrder : MutableList<Int> = mutableListOf(),
    var description : String = "",
    ) {

    fun getSection(position: Int): WorkoutSection? {
        if (position > sectionOrder.size || position < 0)
            return null
        return sections[sectionOrder[position]]
    }
}