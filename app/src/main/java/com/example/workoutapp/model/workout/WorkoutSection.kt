package com.example.workoutapp.model.workout

data class WorkoutSection(
    var name: String = "",
    var type: WorkoutSectionType = WorkoutSectionType.CHECK,
    var number: Int = 0,
    var numberFormatString : String = "",
    var description: String = "",
    ) {

    fun getFormattedNumber() : String {
        return numberFormatString.format(number)
    }
}

enum class WorkoutSectionType {
    TIMER, CHECK
}