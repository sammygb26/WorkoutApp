package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentEditWorkoutBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.edit.EditWorkoutReorderHelperCallback
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
    ): View? {
        _binding = FragmentEditWorkoutBinding.inflate(inflater, container, false)

        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.edit_workout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val id = menuItem.itemId
                when (id) {
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
        requireActivity().title = "Edit"
        requireActivity().addMenuProvider(menuProvider)


        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        supportActionBar.setDisplayShowHomeEnabled(true)
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_editWorkoutFragment_to_workoutOverviewFragment)
    }

    override fun onDestroyView() {
        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        supportActionBar.setDisplayShowHomeEnabled(false)
        supportActionBar.setDisplayHomeAsUpEnabled(false)

        requireActivity().removeMenuProvider(menuProvider)

        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycleView.adapter = EditWorkoutAdapter(editViewModel)
        binding.recycleView.layoutManager = LinearLayoutManager(context)

        ItemTouchHelper(ReorderHelperCallback(
            EditWorkoutReorderHelperCallback(editViewModel)
        ))
            .attachToRecyclerView(binding.recycleView)
    }
}