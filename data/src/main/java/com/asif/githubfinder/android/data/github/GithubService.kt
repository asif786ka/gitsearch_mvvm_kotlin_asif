package com.asif.githubfinder.android.data.github

import com.asif.githubfinder.domain.models.SearchUsersResult
import com.asif.githubfinder.domain.models.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface GithubService {

    @GET("search/users")
    fun searchUsers(@Query("q") query: String): Call<SearchUsersResult>

    @GET("users/{username}")
    fun getUser(@Path("username") username: String): Call<User>
}