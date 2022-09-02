package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutapp.databinding.SheetEditWorkoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class EditWorkoutSheet : BottomSheetDialogFragment(){

    private var _binding: SheetEditWorkoutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetEditWorkoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "EditWorkoutSheet"
    }
}