package com.android.decagon.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.decagon.Model.Responses.DataListResponseModel
import com.android.decagon.Model.Responses.UserResponseModel
import com.android.decagon.Repository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class UserViewModel : ViewModel() {
    private val repository: Repository =
        Repository()

    fun getUsers(
        page: Int? = 0
    ): LiveData<DataListResponseModel<UserResponseModel>?> {
        return liveData(Dispatchers.IO) {
            try {
                val registerResponse =
                    repository.getUsers(page)
                emit(registerResponse)
            } catch (e: HttpException) {
                val errorBody = e.response()?.errorBody()
                if (errorBody != null) {
                    try {
                        val type =
                            object : TypeToken<DataListResponseModel<UserResponseModel>>() {}.type
                        val errorResponse =
                            Gson().fromJson<DataListResponseModel<UserResponseModel>>(
                                errorBody.string(),
                                type
                            )
                        emit(errorResponse)
                    } catch (e: java.lang.Exception) {
                        emit(null)
                    }
                }
            }
        }

    }

}