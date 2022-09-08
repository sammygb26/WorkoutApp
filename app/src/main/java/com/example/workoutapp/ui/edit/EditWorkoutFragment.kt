package com.example.workoutapp.ui.edit

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentEditWorkoutBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.workout.WorkoutViewModel
import com.example.workoutapp.ui.edit.dialog.EditWorkoutDialogAdapter
import com.example.workoutapp.ui.reorder.ReorderHelperCallback

class EditWorkoutFragment : Fragment() {
    private var _binding: FragmentEditWorkoutBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuProvider: MenuProvider

    private var dialog : Dialog? = null

    private val editViewModel: EditViewModel by activityViewModels()
    private val workoutViewModel: WorkoutViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditWorkoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuProvider = object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.edit_workout_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.action_cancel -> {
                        goToHome()
                    }
                    R.id.action_edit -> {
                        goToEditSections()
                    }
                    else -> {
                        editViewModel.saveWorkout()
                        workoutViewModel.startWorkout(editViewModel.workout!!)
                        goToHome()
                    }
                }
                return true
            }
        }
        requireActivity().title = "Edit Workout"
        requireActivity().addMenuProvider(menuProvider)

        val supportActionBar = (requireActivity() as AppCompatActivity).supportActionBar!!
        supportActionBar.setDisplayShowHomeEnabled(true)
        supportActionBar.setDisplayHomeAsUpEnabled(true)

        val adapter = EditWorkoutAdapter(editViewModel)

        binding.apply {
            recycleView.adapter = adapter
            recycleView.layoutManager = LinearLayoutManager(context)
        }
        binding.editWorkoutFragment = this

        ItemTouchHelper(ReorderHelperCallback(
            EditWorkoutReorderHelperCallback(editViewModel, adapter)
        ))
            .attachToRecyclerView(binding.recycleView)
    }

    private fun goToHome() {
        findNavController().navigate(R.id.action_editWorkoutFragment_to_workoutOverviewFragment)
    }

    private fun goToEditSections() {
        findNavController().navigate(R.id.action_editWorkoutFragment_to_editWorkoutSectionsFragment)
    }

    fun openNewSectionReferenceDialog() {
        if (editViewModel.workout!!.sections.isEmpty()) {
            goToEditSections()
            return
        }


        dialog = Dialog(requireContext())


        dialog!!.setContentView(R.layout.dialog_section_list)
        dialog!!.setCancelable(true)

        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.90).toInt()
        dialog!!.window!!.setLayout(width, height)

        val recyclerView = dialog!!.findViewById<RecyclerView>(R.id.recycleView)!!
        recyclerView.adapter = EditWorkoutDialogAdapter(editViewModel, this)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)

        dialog!!.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            dialog!!.dismiss()
        }

        dialog!!.show()
    }

    fun addSectionReference(sectionIndex: Int) {
        editViewModel.workout!!.sectionOrder.add(sectionIndex)
        binding.recycleView.adapter!!.notifyItemInserted(editViewModel.workout!!.sectionOrder.size - 1)

        if (dialog != null)
            dialog!!.dismiss()
    }



    override fun onDestroyView() {
        requireActivity().removeMenuProvider(menuProvider)

        if (dialog != null)
            dialog!!.dismiss()

        super.onDestroyView()
        _binding = null
    }
}