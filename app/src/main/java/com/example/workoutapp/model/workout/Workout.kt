package com.example.workoutapp.model.workout

data class Workout(
    var name: String,
    var sections: List<WorkoutSection> = listOf(),
    var description : String = "",
    ) {

    fun getSection(position: Int): WorkoutSection? {
        if (position >= sections.size || position < 0)
            return null
        return sections[position]
    }
}