package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class IssueAdapter(
    private val issueList: List<IssueData>,
    private val context: Context
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
        holder.createrName.text = issue.name
        holder.issueStatus.text = issue.status
        holder.issuePriority.text = issue.isHighPrior
        holder.itemView.setOnClickListener {
            Toast.makeText(context, holder.titleText.text, Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int {
        return issueList.size
    }
}
