package com.example.workoutapp.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.workoutapp.R
import com.example.workoutapp.databinding.FragmentEditSectionBinding
import com.example.workoutapp.model.edit.EditViewModel
import com.example.workoutapp.model.workout.WorkoutSectionType
import java.lang.Exception

class EditSectionFragment : Fragment(){
    private var _binding: FragmentEditSectionBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: EditViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditSectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            editSectionFragment = this@EditSectionFragment

            typeRadioGroup.setOnCheckedChangeListener { _: RadioGroup, _: Int ->
                updateNumberInputState(currentSelectedType())
            }
        }

        updateNumberInputState(currentSelectedType())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateNumberInputState(type: WorkoutSectionType) {
        when (type) {
            WorkoutSectionType.CHECK -> {
                binding.countEditText.isEnabled = true
                binding.minutesEditText.isEnabled = false
            }
            WorkoutSectionType.TIMER -> {
                binding.minutesEditText.isEnabled = true
                binding.countEditText.isEnabled = false
            }
        }
    }

    fun addSectionToWorkout() {
        sharedViewModel.setSectionName(binding.nameEditText.text.toString())
        sharedViewModel.setSectionDescription(binding.descriptionEditText.text.toString())
        sharedViewModel.setFormatString(binding.formatStringEditText.text.toString())

        val type =  currentSelectedType()
        val number = if (type == WorkoutSectionType.CHECK) binding.countEditText.text.toString() else binding.minutesEditText.text.toString()

        sharedViewModel.setSectionType(type)
        sharedViewModel.setSectionNumber(safeIntConvert(number))

        sharedViewModel.appendCurrentSection()


        goToHome()
    }

    private fun currentSelectedType() =
        if (binding.checkRadioButton.isChecked) WorkoutSectionType.CHECK else WorkoutSectionType.TIMER

    private fun safeIntConvert(text: String, default : Int = 0) : Int {
        return try {
            text.toInt()
        } catch (e : Exception) {
            default
        }
    }

    fun goToHome() {
        findNavController().navigate(R.id.action_editSectionFragment_to_editWorkoutFragment)
    }
}