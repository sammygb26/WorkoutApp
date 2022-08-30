package com.example.workoutapp.ui.workout

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentWorkoutTimerBinding
import com.example.workoutapp.model.workout.WorkoutSectionType
import com.example.workoutapp.model.workout.WorkoutViewModel


class WorkoutTimerFragment : Fragment() {

    private var _binding: FragmentWorkoutTimerBinding? = null
    private val binding: FragmentWorkoutTimerBinding get() = _binding!!

    private val sharedViewModel: WorkoutViewModel by activityViewModels()

    private var cancelWindow: AlertDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutTimerBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            workoutTimerFragmnet = this@WorkoutTimerFragment
        }

        val countdownObserver = Observer<Boolean> {
            countDownUpdate(it)
        }
        sharedViewModel.timerFinished.observe(viewLifecycleOwner, countdownObserver)

        requireActivity().title = sharedViewModel.workoutName
    }

    private fun countDownUpdate(timerFinished : Boolean) {
        if (timerFinished) {
            toNextSection()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun skipTimer() {
        sharedViewModel.endTimer()
    }

    private fun toNextSection() {
        sharedViewModel.nextSection()
        goToSectionOfType(sharedViewModel.getCurrentSectionType())
    }

    fun toPreviousSection() {
        sharedViewModel.previousSection()
        goToSectionOfType(sharedViewModel.getCurrentSectionType())
    }

    private fun goToSectionOfType(type: WorkoutSectionType?) {
        val nextNavID = when (type) {
            WorkoutSectionType.CHECK -> R.id.action_workoutTimerFragment_to_workoutCheckFragment
            WorkoutSectionType.TIMER -> R.id.action_workoutTimerFragment_self
            null -> R.id.action_workoutTimerFragment_to_workoutOverviewFragment
        }

        if (cancelWindow != null) {
            cancelWindow!!.dismiss()
        }

        findNavController().navigate(nextNavID)
    }

    fun cancel() {
        val alertDialogBuilder = AlertDialog.Builder(context)

        val positiveClick = { _: DialogInterface, _: Int ->
            goToSectionOfType(null)
        }

        alertDialogBuilder.setMessage(getString(R.string.quit_workout_check_message))
        alertDialogBuilder.setPositiveButton(getString(R.string.yes), positiveClick)
        cancelWindow = alertDialogBuilder.show()
    }
}