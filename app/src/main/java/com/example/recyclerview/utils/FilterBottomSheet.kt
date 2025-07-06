package com.example.recyclerview.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import com.example.recyclerview.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheet : BottomSheetDialogFragment() {

    private lateinit var applyButton: Button
    private lateinit var statusOpen: CheckBox
    private lateinit var statusClosed: CheckBox
    private lateinit var priorityHigh: CheckBox
    private lateinit var priorityLow: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_filter, container, false)

        applyButton = view.findViewById(R.id.apply_button)
        statusOpen = view.findViewById(R.id.checkbox_open)
        statusClosed = view.findViewById(R.id.checkbox_closed)
        priorityHigh = view.findViewById(R.id.checkbox_high)
        priorityLow = view.findViewById(R.id.checkbox_low)

        val checkboxes = listOf(statusOpen, statusClosed, priorityHigh, priorityLow)
        checkboxes.forEach {
            it.setOnCheckedChangeListener { _, _ ->
                updateApplyButtonState()
            }
        }
        applyButton.setOnClickListener {
            val selectedStatus = if (statusOpen.isChecked) "Open" else "Closed"
            val selectedPriority = if (priorityHigh.isChecked) "High" else "Low"

            Toast.makeText(requireContext(), "Filter: $selectedStatus, $selectedPriority", Toast.LENGTH_SHORT).show()

            dismiss() // Close bottom sheet
        }

        return view
    }

    private fun updateApplyButtonState() {
        val isAnyFilterSelected = statusOpen.isChecked || statusClosed.isChecked || priorityHigh.isChecked || priorityLow.isChecked
        applyButton.isEnabled = isAnyFilterSelected
    }
}