// HomeViewModel.kt
package com.example.recyclerview.ui.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.recyclerview.activities.HomeActivity
import com.example.recyclerview.activities.MainActivity
import com.example.recyclerview.data.DeleteRes
import com.example.recyclerview.data.Issue
import com.example.recyclerview.data.IssueData
import com.example.recyclerview.data.IssueResponse
import com.example.recyclerview.data.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    var selectedIssue: Issue? = null
    var isLoggedIn: Boolean = true;
    var issueList = mutableListOf<Issue>()

    fun fetchIssues(callback: (List<Issue>) -> Unit) {
        RetrofitClient.api.getAllIssues().enqueue(object : Callback<IssueResponse> {
            override fun onResponse(call: Call<IssueResponse>, response: Response<IssueResponse>) {
                if (response.isSuccessful) {
                    val issues = response.body()?.data ?: emptyList()
                    issueList = issues.toMutableList();
                    callback(issues)
                }
            }

            override fun onFailure(call: Call<IssueResponse>, t: Throwable) {
                callback(emptyList())
            }
        })
    }
    fun deleteIssue(issueId : String?){
        //if(issueId != null){
          //  RetrofitClient.api.deleteIssue(issueId).enqueue(object : Callback<DeleteRes>){
            //    override fun onResponse(call : Callback<>)
            //}

        //}
    }
    fun addNewIssue(){

    }

    fun handleLogOut(context : Context) {
        val prefs = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
        prefs.edit().remove("token").apply()
        isLoggedIn = false;
    }




}