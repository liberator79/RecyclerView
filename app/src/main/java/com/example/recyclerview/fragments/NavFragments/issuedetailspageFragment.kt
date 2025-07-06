package com.example.recyclerview.fragments.NavFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.recyclerview.R
import com.example.recyclerview.utils.IssueFileDao

class issuedetailspageFragment : Fragment() {
    private lateinit var title : String
    private lateinit var status : String
    private lateinit var description : String
    private lateinit var priority : String
    private var position :Int  = -1;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            title = it.getString("title", "");
            status = it.getString("status", "");
            description = it.getString("description", "");
            priority = it.getString("priority", "");
            position = it.getInt("position", 0);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_issuedetailspage, container, false)
        val dao = IssueFileDao(requireContext());
        view.findViewById<TextView>(R.id.ticket_title).text = title
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
        view.findViewById<TextView>(R.id.ticket_description).text = description

        view.findViewById<ImageView>(R.id.delete_icon).setOnClickListener {
            if(position != -1)dao.deleteIssueAt(position);
            requireActivity().supportFragmentManager.popBackStack()
        }

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