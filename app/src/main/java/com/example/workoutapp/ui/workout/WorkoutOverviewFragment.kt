package com.example.workoutapp.ui.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentWorkoutOverviewBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.workout.WorkoutSectionType
import com.example.workoutapp.model.workout.WorkoutViewModel

class WorkoutOverviewFragment: Fragment() {
    private var _binding: FragmentWorkoutOverviewBinding? = null
    private val binding: FragmentWorkoutOverviewBinding get() = _binding!!

    private val workoutViewModel: WorkoutViewModel by activityViewModels()
    private val editViewModel: EditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorkoutOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = workoutViewModel
            workoutStartFragment = this@WorkoutOverviewFragment
        }

        requireActivity().title = "Overview"
        workoutViewModel.resetWorkout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun toNextSection() {
        val nextNavID = when(workoutViewModel.getCurrentSectionType()) {
            WorkoutSectionType.CHECK -> R.id.action_workoutOverviewFragment_to_workoutCheckFragment
            WorkoutSectionType.TIMER -> R.id.action_workoutOverviewFragment_to_workoutTimerFragment
            null -> return
        }
        findNavController().navigate(nextNavID)
    }

    fun toEditWorkout() {
        editViewModel.initializeWorkoutEdit(requireContext(), workoutViewModel.workoutName)
        findNavController().navigate(R.id.action_workoutOverviewFragment_to_editWorkoutFragment)
    }
}