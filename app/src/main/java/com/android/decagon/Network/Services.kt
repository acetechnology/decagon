package com.android.decagon.Network

import com.android.decagon.Model.Responses.DataListResponseModel
import com.android.decagon.Model.Responses.UserResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface Services {

    @GET("article_users/search")
    suspend fun getUsers(
        @Query("page") pageNumber: Int? = 0
    ): DataListResponseModel<UserResponseModel>
}