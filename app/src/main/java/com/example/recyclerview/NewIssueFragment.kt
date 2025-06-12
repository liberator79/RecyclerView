package com.example.recyclerview

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File



class NewIssueFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_issue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val issueTitle : EditText = view.findViewById(R.id.new_issue_title)
        val issueDescription : EditText = view.findViewById(R.id.new_issue_des)
        val isHighPrior : CheckBox = view.findViewById(R.id.checkbox_terms)
        val newIssueSubmit : Button = view.findViewById(R.id.btn_submit)
        newIssueSubmit.setOnClickListener {
            val title = issueTitle.text.toString()
            val description = issueDescription.text.toString()
            val issuePriority = if (isHighPrior.isChecked) "High" else "Low"
            if(title.isNotBlank()){
                val newIssue = IssueData(title, "kal", "open", description, issuePriority);
                saveIssue(requireContext(), newIssue)
            }
        }
    }

    private fun saveIssue(context : Context, newIssue : IssueData){
        val gson = Gson();
        val file = File(context.filesDir, "issue.json");
        val issuesList: MutableList<IssueData> = if (file.exists()) {
            val json = file.readText()
            gson.fromJson(json, object : TypeToken<MutableList<IssueData>>() {}.type)
        } else {
            mutableListOf()
        }
        issuesList.add(newIssue)
        file.writeText(gson.toJson(issuesList))
    }


}