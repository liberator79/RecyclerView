package com.example.recyclerview.fragments.NavFragments

import com.example.recyclerview.utils.FilterBottomSheet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.utils.IssueAdapter
import com.example.recyclerview.data.IssueData
import com.example.recyclerview.R
import com.example.recyclerview.utils.IssueFileDao
import com.example.recyclerview.fragments.NavFragments.issuedetailspageFragment

class IsuuesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var issueList : List<IssueData>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        val view = inflater.inflate(R.layout.fragment_isuues, container, false)
        val filterButton = view.findViewById<TextView>(R.id.filter_button)
        filterButton.setOnClickListener {
            FilterBottomSheet().show(parentFragmentManager, "com.example.recyclerview.utils.FilterBottomSheet")
        }
        val dao = IssueFileDao(requireContext())
        recyclerView = view.findViewById(R.id.issue_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        issueList = dao.getAllIssues();

        val adapter = IssueAdapter(issueList, requireContext()) { selectedIssue, position ->
            loadIssueDetailsFrag(selectedIssue, position)
        }
        recyclerView.adapter = adapter

        return view
    }


    fun loadIssueDetailsFrag(issue: IssueData, position: Int) {
        val bundle = Bundle().apply {
            putString("title", issue.title)
            putString("status", if(issue.status)"open" else "closed")
            putString("priority", if(issue.isHighPrior) "High" else "Low")
            putString("description", issue.description)
            putInt("position", position);
        }

        val detailFragment = issuedetailspageFragment().apply {
            arguments = bundle
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }



}