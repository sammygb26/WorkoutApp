package com.example.workoutapp.ui.start

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentStartBinding
import com.example.workoutapp.model.start.StartViewModel
import com.example.workoutapp.model.workout.Workout
import com.example.workoutapp.model.workout.WorkoutViewModel
import com.example.workoutapp.ui.edit.EditWorkoutSheet
import com.google.android.material.bottomsheet.BottomSheetBehavior


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding get() = _binding!!
    private lateinit var menuProvider : MenuProvider

    private val workoutViewModel : WorkoutViewModel by activityViewModels()
    private val startViewModel : StartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startViewModel.loadWorkouts(requireContext())

        requireActivity().title = "Start"

        binding.apply {
            startFragment = this@StartFragment
            recycleView.adapter = StartWorkoutListAdapter(startViewModel, this@StartFragment)
            recycleView.layoutManager = LinearLayoutManager(context)
        }

        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.start_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val id = menuItem.itemId
                when (id) {
                    R.id.action_add -> launchNewWorkoutSheet()
                }
                return true
            }
        }
        requireActivity().addMenuProvider(menuProvider)
    }

    private fun launchNewWorkoutSheet() {
        val editWorkoutSheet = EditWorkoutSheet()
        editWorkoutSheet.show(requireActivity().supportFragmentManager, EditWorkoutSheet.TAG)
    }

    override fun onDestroyView() {
        requireActivity().removeMenuProvider(menuProvider)
        super.onDestroyView()
        _binding = null
    }

    fun goToWorkout(name: String) {
        val workout = startViewModel.getWorkout(name)

        if (workout != null) {
            openWorkout(workout)
        }
    }

    private fun openWorkout(workout: Workout) {
        workoutViewModel.startWorkout(workout)
        findNavController().navigate(R.id.action_startFragment_to_workoutOverviewFragment)
    }
}