package com.example.recyclerview.ui.fragments.NavFragments

import com.example.recyclerview.utils.FilterBottomSheet
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
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
import androidx.lifecycle.ViewModel
import com.example.recyclerview.data.Issue
import kotlin.collections.mutableListOf

class IsuuesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var issueList : List<Issue>
    private lateinit var loader : ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_isuues, container, false)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        loader = view.findViewById(R.id.issueLoader)
        //viewModel.fetchIssues()
        val filterButton = view.findViewById<TextView>(R.id.filter_button)
        filterButton.setOnClickListener {
            FilterBottomSheet().show(parentFragmentManager, "FilterBottomSheetDialog")
        }
        recyclerView = view.findViewById(R.id.issue_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        loader.visibility = View.VISIBLE
        viewModel.fetchIssues { issues ->
            issueList = issues
            loader.visibility = View.GONE
            val adapter = IssueAdapter(issueList, requireContext()) { selectedIssue, position ->
                loadIssueDetailsFrag(selectedIssue, position)
            }

            recyclerView.adapter = adapter

        }

    }


    fun loadIssueDetailsFrag(issue: Issue, position: Int) {
        viewModel.selectedIssue = issue
        val detailFragment = issuedetailspageFragment()


        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.home_fragment_container, detailFragment)
            .addToBackStack(null)
            .commit()
    }



}