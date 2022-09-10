package com.example.workoutapp.ui.workout

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
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

    private lateinit var menuProvider: MenuProvider

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
            workoutOverviewFragment = this@WorkoutOverviewFragment
        }

        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.workout_overview_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_edit -> toEditWorkout()
                    else -> goToHome()
                }
                return true
            }

        }
        requireActivity().addMenuProvider(menuProvider)
        requireActivity().title = "Overview"
        workoutViewModel.resetWorkout()



        setHome(true)
    }

    private fun setHome(homeEnabled : Boolean) {

        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        supportActionBar.setDisplayShowHomeEnabled(homeEnabled)
        supportActionBar.setDisplayHomeAsUpEnabled(homeEnabled)
    }

    override fun onDestroyView() {
        requireActivity().removeMenuProvider(menuProvider)
        super.onDestroyView()
        _binding = null
    }

    fun toNextSection() {
        setHome(false)

        val nextNavID = when(workoutViewModel.getCurrentSectionType()) {
            WorkoutSectionType.CHECK -> R.id.action_workoutOverviewFragment_to_workoutCheckFragment
            WorkoutSectionType.TIMER -> R.id.action_workoutOverviewFragment_to_workoutTimerFragment
            null -> return
        }
        findNavController().navigate(nextNavID)
    }

    fun goToHome() {
        setHome(false)
        findNavController().navigate(R.id.action_workoutOverviewFragment_to_startFragment)
    }

    fun toEditWorkout() {
        editViewModel.initializeWorkoutEdit(requireContext(), workoutViewModel.workoutName)
        findNavController().navigate(R.id.action_workoutOverviewFragment_to_editWorkoutFragment)
    }
}