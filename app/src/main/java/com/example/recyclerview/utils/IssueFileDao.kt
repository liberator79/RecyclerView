package com.example.recyclerview.utils

import android.content.Context
import com.example.recyclerview.data.IssueData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

class IssueFileDao(private val context: Context) {

    private val fileName = "issue.json"
    private val gson = Gson()

    private fun getFile(): File {
        return File(context.filesDir, fileName)
    }

    private fun loadIssues(): MutableList<IssueData> {
        val file = getFile()
        if (!file.exists()) return mutableListOf()

        return try {
            val json = file.readText()
            gson.fromJson(json, object : TypeToken<MutableList<IssueData>>() {}.type) ?: mutableListOf()
        } catch (e: Exception) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    private fun saveIssues(issues: List<IssueData>) {
        try {
            val json = gson.toJson(issues)
            getFile().writeText(json)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAllIssues(): List<IssueData> {
        return loadIssues()
    }

    fun addIssue(issue: IssueData) {
        val issues = loadIssues()
        issues.add(issue)
        saveIssues(issues)
    }

    fun deleteIssueAt(position: Int) {
        val issues = loadIssues().toMutableList()
        if (position in issues.indices) {
            issues.removeAt(position)
            saveIssues(issues)
        }
    }
}