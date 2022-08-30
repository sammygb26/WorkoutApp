package com.example.workoutapp.data

import com.example.workoutapp.model.workout.Workout
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutSectionType

object WorkoutDataSource {
    val basicWorkout get() = Workout(
        "Simple Workout (Push-ups)",
        mutableListOf(
            WorkoutSection(
                "Run to pull up bar",
                WorkoutSectionType.CHECK,
                0,
                "Run to pull up bar in the meadows.",
                "This works as a good warm up. Make sure to run the long way around trying not to stop at all."
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
            "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Run",
                WorkoutSectionType.CHECK,
                0,
                "Run usual route around hollyrood and back to pull-up bar."
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
            WorkoutSection(
                "Pull-ups",
                WorkoutSectionType.CHECK,
                8,
                "%s pull-ups"
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
            ),
        ),
        "Basic workout involving different types of activies."
    )
}