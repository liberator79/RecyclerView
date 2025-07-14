package com.example.recyclerview.data

data class user (
    val id : String,
    val email : String
)

data class Issue (
    val id : String,
    val title : String,
    val description : String,
    val isHighPrior : Boolean?,
    val status : Boolean?,
    val userId : String,
    val createdAt : String,
    val user : user
)

data class DeleteReq(
    val issueId : String
)

data class DeleteRes(
    val success : Boolean
)

data class IssueResponse (
    val success : Boolean,
    val data : List<Issue>
)
