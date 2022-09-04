package com.example.workoutapp.data

import com.example.workoutapp.model.workout.Workout
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutSectionType

object WorkoutDataSource {
    val basicWorkout get() = Workout(
        "Simple Workout (Push-ups)",
        mutableListOf(
            WorkoutSection(
                "Push-ups",
                WorkoutSectionType.CHECK,
                10,
                "%s push-ups",
                "Try to keep the pace up and not stop between push-ups."
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
                "",
                "Rest now, but try to keep moving."
            ),
            WorkoutSection(
                "Push-ups",
                WorkoutSectionType.CHECK,
                10,
                "%s push-ups",
                "Try to keep the pace up and not stop between push-ups."
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
                "",
                "Rest now, but try to keep moving."
            ),
            WorkoutSection(
                "Push-ups",
                WorkoutSectionType.CHECK,
                10,
                "%s push-ups",
                "Try to keep the pace up and not stop between push-ups."
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
                "",
                "Rest now, but try to keep moving."
            ),
            WorkoutSection(
                "Push-ups",
                WorkoutSectionType.CHECK,
                10,
                "%s push-ups",
                "Try to keep the pace up and not stop between push-ups."
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
                "",
                "Rest now, but try to keep moving."
            ),
            WorkoutSection(
                "Push-ups",
                WorkoutSectionType.CHECK,
                10,
                "%s push-ups",
                "Try to keep the pace up and not stop between push-ups."
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
                "",
                "Rest now, but try to keep moving."
            ),
            WorkoutSection(
                "Push-ups",
                WorkoutSectionType.CHECK,
                10,
                "%s push-ups",
                "Try to keep the pace up and not stop between push-ups."
            ),
            WorkoutSection(
                "Rest",
                WorkoutSectionType.TIMER,
                60,
                "",
                "Rest now, but try to keep moving."
            ),
        ),
        mutableListOf(0,1,0,1,0,1,0,1,0,1),
        "Basic workout involving different types of activies."
    )
}