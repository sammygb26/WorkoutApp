package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workoutapp.databinding.FragmentEditSectionBinding
import com.example.workoutapp.databinding.FragmentEditWorkoutSectionsBinding
import com.example.workoutapp.model.edit.EditViewModel

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

        binding.recycleView.adapter = EditWorkoutSectionsAdapter(editViewModel)
        binding.recycleView.layoutManager = LinearLayoutManager(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}