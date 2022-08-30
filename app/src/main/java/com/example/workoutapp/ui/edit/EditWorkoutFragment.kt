package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentEditWorkoutBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.workout.WorkoutSection
import com.example.workoutapp.model.workout.WorkoutViewModel
import com.example.workoutapp.ui.reorder.ReorderHelperCallback

class EditWorkoutFragment : Fragment() {
    private var _binding: FragmentEditWorkoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuProvider: MenuProvider

    private val editViewModel: EditViewModel by activityViewModels()
    private val workoutViewModel: WorkoutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditWorkoutBinding.inflate(inflater, container, false)

        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.edit_workout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_save -> {
                        editViewModel.saveWorkout()
                        workoutViewModel.startWorkout(editViewModel.workout!!)
                    }
                    else ->
                        goToHome()
                }
                return true
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Edit"
        requireActivity().addMenuProvider(menuProvider)

        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        supportActionBar.setDisplayShowHomeEnabled(true)
        supportActionBar.setDisplayHomeAsUpEnabled(true)


        binding.recycleView.adapter = EditWorkoutAdapter(editViewModel)
        binding.recycleView.layoutManager = LinearLayoutManager(context)
        binding.editWorkoutFragment = this

        ItemTouchHelper(ReorderHelperCallback(
            EditWorkoutReorderHelperCallback(editViewModel)
        ))
            .attachToRecyclerView(binding.recycleView)
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_editWorkoutFragment_to_workoutOverviewFragment)
    }

    fun addNewSection() {
        val section = WorkoutSection()
        editViewModel.initializeSectionEdit(section)
        findNavController().navigate(R.id.action_editWorkoutFragment_to_editWorkoutSectionsFragment)
    }

    override fun onDestroyView() {
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        supportActionBar.setDisplayShowHomeEnabled(false)
        supportActionBar.setDisplayHomeAsUpEnabled(false)

        requireActivity().removeMenuProvider(menuProvider)

        super.onDestroyView()
        _binding = null
    }
}