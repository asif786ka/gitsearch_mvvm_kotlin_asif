package com.asif.githubfinder.domain.usecases

import com.asif.githubfinder.domain.interfaces.GithubDataSource

class GetUserUseCase(private val githubDataSource: GithubDataSource) {
    operator fun invoke(username: String) = githubDataSource.getUser(username)
}