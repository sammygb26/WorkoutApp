package com.example.workoutapp.model.workout

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

const val NO_TIMER_SUBDIVISIONS = 3600

class WorkoutViewModel : ViewModel() {


    // Progress bar
    private val _workoutProgress : MutableLiveData<Int> = MutableLiveData<Int>(0)
    val workoutProgress: LiveData<Int> = _workoutProgress


    // Timer
    private val _timerProgress : MutableLiveData<Int> = MutableLiveData<Int>(0)
    val timerProgress : LiveData<Int> = _timerProgress

    private val _timerCountdown : MutableLiveData<Int> = MutableLiveData<Int>(0)
    val timerCountdown : LiveData<String> = Transformations.map(_timerCountdown) { it.toString() }

    val timerFinished : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    private var timeRemaining : Long = 0
    private var length : Long = 0
    private var timer : CountDownTimer? = null

    // Other
    private var workoutPosition = 0
    private var workout: Workout? = null

    val currentSection: WorkoutSection? get() = workout!!.getSection(workoutPosition)
    val workoutName: String get() = workout!!.name

    fun startWorkout(workout: Workout) {
        this.workout = workout
        resetWorkout()
    }

    fun getWorkoutPosition() : Int = workoutPosition

    fun resetWorkout() {
        workoutPosition = 0
        _workoutProgress.value = 0
    }

    fun nextSection() {
        workoutPosition++
        if (currentSection != null)
            initSection()
    }

    fun previousSection() {
        workoutPosition--
        if (currentSection != null)
            initSection()
    }

    private fun initSection() {
        val progressFraction = (workoutPosition.toFloat() / workout!!.sections.size.toFloat())
        _workoutProgress.value = (progressFraction * NO_TIMER_SUBDIVISIONS).toInt()

        if (currentSection!!.type == WorkoutSectionType.TIMER) {
            timerFinished.value = false
            startTimer(currentSection!!.number.toLong() * 1000)
        }
    }

    fun getCurrentSectionType() : WorkoutSectionType? {
        return workout!!.getSection(workoutPosition)?.type
    }

    // Timer

    private fun startTimer(timerLength : Long) {
        _timerProgress.value = NO_TIMER_SUBDIVISIONS
        timerFinished.value = false

        length = timerLength
        timeRemaining = timerLength

        resumeTimer()
    }

    private fun resumeTimer() {
        setTimer(timeRemaining)
        timer!!.start()
    }

    private fun setTimer(timerLength: Long) {
        if (timer != null)
            timer!!.cancel()

        timer = object : CountDownTimer(timerLength, 10) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished

                val progressFraction : Float = (millisUntilFinished.toFloat() / length.toFloat())
                _timerProgress.value = (progressFraction * NO_TIMER_SUBDIVISIONS).toInt()
                _timerCountdown.value = (progressFraction * length / 1000).toInt()
            }

            override fun onFinish() {
                timerFinished.value = true
            }
        }
    }

    fun endTimer() {
        _timerProgress.value = 0
        _timerCountdown.value = 0
        timerFinished.value = true

        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }
}