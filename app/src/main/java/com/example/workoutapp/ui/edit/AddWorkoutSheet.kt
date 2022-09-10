package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.workoutapp.databinding.SheetAddWorkoutBinding
import com.example.workoutapp.model.start.StartViewModel
import com.example.workoutapp.model.workout.Workout
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddWorkoutSheet : BottomSheetDialogFragment(){

    private var _binding: SheetAddWorkoutBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: StartViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetAddWorkoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            editWorkoutSheet = this@AddWorkoutSheet
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun addWorkout() {
        val name = binding.nameEditText.text.toString()
        val description = binding.nameEditText.text.toString()

        val workout = Workout(name=name, description=description)
        sharedViewModel.addWorkout(workout, requireContext())

        this.dismiss()
    }

    companion object {
        const val TAG = "AddWorkoutSheet"
    }
}