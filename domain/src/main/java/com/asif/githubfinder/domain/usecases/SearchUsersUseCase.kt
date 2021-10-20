package com.asif.githubfinder.domain.usecases

import com.asif.githubfinder.common.Logger
import com.asif.githubfinder.domain.interfaces.GithubDataSource
import com.asif.githubfinder.domain.interfaces.SearchHistoryDataSource
import com.asif.githubfinder.domain.models.SearchHistoryItem
import com.asif.githubfinder.domain.models.SearchUsersResult

class SearchUsersUseCase(
    private val githubDataSource: GithubDataSource,
    private val searchHistoryDataSource: SearchHistoryDataSource,
    private val logger: Logger
) {
    operator fun invoke(query: String): SearchUsersResult {
        try {
            searchHistoryDataSource.add(SearchHistoryItem(query = query))
            logger.info("Query added to the search history")
        } catch (e: Exception) {
            logger.warn("An error occurred while adding a query to the search history", e)
        }
        return githubDataSource.searchUsers(query)
    }
}