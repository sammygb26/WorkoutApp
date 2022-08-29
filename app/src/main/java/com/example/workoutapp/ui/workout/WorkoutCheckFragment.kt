package com.example.workoutapp.ui.workout

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentWorkoutCheckBinding
import com.example.workoutapp.model.workout.WorkoutSectionType
import com.example.workoutapp.model.workout.WorkoutViewModel

class WorkoutCheckFragment: Fragment() {
    private var _binding: FragmentWorkoutCheckBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: WorkoutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWorkoutCheckBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            workoutCheckFragment = this@WorkoutCheckFragment
            viewModel = sharedViewModel
        }

        requireActivity().title = sharedViewModel.workoutName
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun toNextSection() {
        sharedViewModel.nextSection()
        goToSectionOfType(sharedViewModel.getCurrentSectionType())
    }

    fun toPreviousSection() {
        sharedViewModel.previousSection()
        goToSectionOfType(sharedViewModel.getCurrentSectionType())
    }

    private fun goToSectionOfType(type: WorkoutSectionType?) {
        val nextNavID = when(type) {
            WorkoutSectionType.CHECK -> R.id.action_workoutCheckFragment_self
            WorkoutSectionType.TIMER -> R.id.action_workoutCheckFragment_to_workoutTimerFragment
            null -> R.id.action_workoutCheckFragment_to_workoutOverviewFragment
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
        alertDialogBuilder.show()
    }
}