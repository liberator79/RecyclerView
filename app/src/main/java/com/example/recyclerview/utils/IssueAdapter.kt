package com.example.recyclerview.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.R
import com.example.recyclerview.data.Issue
import com.example.recyclerview.data.IssueData

class IssueAdapter(
    private val issueList: List<Issue>,
    private val context: Context,
    private val onItemClick: (Issue, Int) -> Unit
) : RecyclerView.Adapter<IssueAdapter.IssuesHolder>() {

    class IssuesHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleText: TextView = itemView.findViewById(R.id.issue_title)
        val createrName: TextView = itemView.findViewById(R.id.issue_creater)
        val issuePriority: TextView = itemView.findViewById(R.id.issue_priority)
        val issueStatus: TextView = itemView.findViewById(R.id.issue_status)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.issue_card_design, parent, false)
        return IssuesHolder(view)
    }

    override fun onBindViewHolder(holder: IssuesHolder, position: Int) {
        val issue = issueList[position]
        holder.titleText.text = issue.title
        holder.createrName.text = issue.user.email
        holder.issueStatus.text = if(true) "Open" else "Closed"
        holder.issuePriority.text = if(true) "High" else "Low"
        holder.itemView.setOnClickListener {
            Toast.makeText(context, holder.titleText.text, Toast.LENGTH_SHORT).show()
            onItemClick(issue, position);
        }

    }

    override fun getItemCount(): Int {
        return issueList.size
    }
}