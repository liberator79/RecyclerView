package com.example.recyclerview.ui.fragments.NavFragments

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
import com.example.recyclerview.ui.viewmodel.HomeViewModel
import com.example.recyclerview.utils.IssueFileDao
import androidx.fragment.app.activityViewModels

class IsuuesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var issueList : List<IssueData>
    private val viewModel: HomeViewModel by activityViewModels()
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
        viewModel.selectedIssue = issue
        val detailFragment = issuedetailspageFragment()


        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }



}