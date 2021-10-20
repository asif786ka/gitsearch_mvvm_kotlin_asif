package com.asif.githubfinder.domain.interfaces

import com.asif.githubfinder.domain.models.SearchUsersResult
import com.asif.githubfinder.domain.models.User

interface GithubDataSource {

    fun searchUsers(query: String): SearchUsersResult

    fun getUser(username: String): User
}