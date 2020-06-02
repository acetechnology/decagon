package com.android.decagon.Model.Responses

data class UserResponseModel(
    val id: Int,
    val username: String,
    val about: String,
    val submitted: Int,
    val updated_at: String,
    val submission_count: Int,
    val comment_count: Int,
    val created_at: Int
)