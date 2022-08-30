package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentEditSectionBinding
import com.example.workoutapp.databinding.FragmentEditWorkoutSectionsBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.workout.WorkoutSection

class EditWorkoutSectionsFragment : Fragment(){
    private var _binding: FragmentEditWorkoutSectionsBinding? = null
    private val binding get() = _binding!!

    private val editViewModel: EditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addSectionToOrder(name: String) {
        editViewModel.appendSectionToOrder(name)
        goToHome()
    }

    fun goToHome() {
        findNavController().navigate(R.id.action_editWorkoutSectionsFragment_to_editWorkoutFragment)
    }
}