package com.android.decagon

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.decagon.ViewModels.UserViewModel
import java.text.SimpleDateFormat
import java.util.*

class TechStageOneActivity : AppCompatActivity() {

    private var userViewModel: UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userViewModel = this?.let { ViewModelProvider(it).get(UserViewModel::class.java) }
        getUsernames()
    }


    private fun getUsernames() {
        if (isNetworkAvailable()) {
            userViewModel?.getUsers(page = 0)
                ?.observe(this, Observer { responseModel ->
                    if (responseModel != null) {

                        println("-----------getUsernames-----------------------------")
                        val sort_by_most_active =
                            responseModel.data.sortedByDescending { it -> it.submission_count }
                                .map { it.username + " " + it.submission_count }

                        //most active user based on submission_count
                        for (name_with_submission_count in sort_by_most_active) {
                            println(name_with_submission_count + " sorted by submission count")
                        }

                        println("-------getUsernameWithHighestCommentCount-----------------------------")

                        val sort_usernames_by_highest_comment =
                            responseModel.data.sortedByDescending { it -> it.comment_count }
                                .map { it.username + " " + it.comment_count }

                        //user sorted based on highest comment_count
                        for (name_with_highest_comment_count in sort_usernames_by_highest_comment) {
                            println(name_with_highest_comment_count + " sorted by comment count")
                        }


                        println("---------getUsernamesSortedByRecordDate-----------------------------")

                        val sort_usernames_by_record_date =
                            responseModel.data.sortedByDescending { it -> it.created_at }
                                .map { it.username + " " + getDateTime((it.created_at.toString())) }

                        //user sorted based on date created
                        for (name_with_highest_comment_count in sort_usernames_by_record_date) {
                            println(name_with_highest_comment_count + " sorted by record date")
                        }

                    } else {
                        Toast.makeText(this, "An error occured", Toast.LENGTH_SHORT).show()

                    }
                })
        }
    }


    private fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            this?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }


    private fun getDateTime(time: String): String? {
        try {
            val format = SimpleDateFormat("MM/dd/yyyy")
            val date = Date(time.toLong() * 1000)
            return format.format(date)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}
