package com.android.decagon.Model.Responses

data class DataListResponseModel<T>(
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<T>
)