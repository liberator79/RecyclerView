package com.example.recyclerview.ui.fragments.NavFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recyclerview.R
import com.example.recyclerview.ui.viewmodel.HomeViewModel
import com.example.recyclerview.utils.IssueFileDao
import kotlin.getValue

class issuedetailspageFragment : Fragment() {
//    private lateinit var title : String
    private lateinit var status : String
//    private lateinit var description : String
    private lateinit var priority : String
    private val viewModel: HomeViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_issuedetailspage, container, false)
        val dao = IssueFileDao(requireContext());
        view.findViewById<TextView>(R.id.ticket_title).text = viewModel.selectedIssue?.title
        status = if(viewModel.selectedIssue?.status == true)"open" else "closed"
        priority = if(viewModel.selectedIssue?.isHighPrior == true)"High" else "Low"
        val statusColor = when (status) {
            "open" -> ContextCompat.getColor(requireContext(), R.color.status_open)
            else -> ContextCompat.getColor(requireContext(), R.color.status_closed)
        }
        val priorityColor = when (priority){
            "High" -> ContextCompat.getColor(requireContext(), R.color.priority_high)
            else -> ContextCompat.getColor(requireContext(), R.color.priority_low)
        }

        view.findViewById<TextView>(R.id.ticket_status).setTextColor(statusColor)
        view.findViewById<TextView>(R.id.ticket_priority).setTextColor(priorityColor)

        view.findViewById<TextView>(R.id.ticket_status).text = status
        view.findViewById<TextView>(R.id.ticket_priority).text = priority
        view.findViewById<TextView>(R.id.ticket_description).text = viewModel.selectedIssue?.description

        view.findViewById<ImageView>(R.id.delete_icon).setOnClickListener {
            viewModel.deleteIssue(viewModel.selectedIssue?.id);
            requireActivity().supportFragmentManager.popBackStack()
        }
        if(viewModel.selectedIssue != null)Log.d("Issuedetails", "Data is not null")
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            issuedetailspageFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}