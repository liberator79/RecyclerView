// HomeViewModel.kt
package com.example.recyclerview.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.recyclerview.data.IssueData

class HomeViewModel : ViewModel() {
    var selectedIssue: IssueData? = null
}