package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentEditWorkoutSectionsBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.workout.WorkoutSection

class EditWorkoutSectionsFragment : Fragment(){
    private var _binding: FragmentEditWorkoutSectionsBinding? = null
    private val binding get() = _binding!!
    private lateinit var menuProvider: MenuProvider

    private val editViewModel: EditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditWorkoutSectionsBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recycleView.adapter = EditWorkoutSectionsAdapter(editViewModel, this)
        binding.recycleView.layoutManager = LinearLayoutManager(context)
        binding.editWorkoutSectionsFragment = this

        menuProvider = object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.edit_workout_sections_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when(menuItem.itemId) {
                    R.id.action_add -> launchAddSectionSheet()
                    else -> goToHome()
                }
                return true
            }
        }
        requireActivity().title = "Sections"
        requireActivity().addMenuProvider(menuProvider)
    }

    override fun onDestroyView() {
        requireActivity().removeMenuProvider(menuProvider)

        super.onDestroyView()
        _binding = null
    }

    fun addSectionToOrder(name: String) {
        editViewModel.appendSectionToOrder(name)
        goToHome()
    }

    fun launchAddSectionSheet() {
        val addSectionSheet = AddSectionSheet()
        addSectionSheet.show(requireActivity().supportFragmentManager, AddSectionSheet.TAG)
    }

    fun goToHome() {
        findNavController().navigate(R.id.action_editWorkoutSectionsFragment_to_editWorkoutFragment)
    }

    fun removeSection(section: WorkoutSection) {
        val position = editViewModel.removeSection(section)
        binding.recycleView.adapter!!.notifyItemRemoved(position)
    }
}