package com.example.recyclerview

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class IsuuesFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var issueList : List<IssueData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_isuues, container, false)

        recyclerView = view.findViewById(R.id.issue_list)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        issueList = loadIssues(requireContext())

        val adapter = IssueAdapter(issueList, requireContext())
        recyclerView.adapter = adapter

        return view
    }

    private fun loadIssues(context: Context): List<IssueData> {
        val file = File(context.filesDir, "issue.json")
        return if (file.exists()) {
            val json = file.readText()
            val gson = Gson()
            gson.fromJson(json, object : TypeToken<List<IssueData>>() {}.type)
        } else {
            emptyList()
        }
    }
}
