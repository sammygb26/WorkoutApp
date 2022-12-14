package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workoutapp.databinding.SheetAddSectionBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddSectionSheet : BottomSheetDialogFragment() {
    private var _binding : SheetAddSectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetAddSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "AddSectionSheet"
    }
}