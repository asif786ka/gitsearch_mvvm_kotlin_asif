package com.asif.githubfinder.domain.usecases

import com.asif.githubfinder.domain.interfaces.SearchHistoryDataSource

class GetSearchHistoryUseCase(private val searchHistoryDataSource: SearchHistoryDataSource) {
    operator fun invoke() = searchHistoryDataSource.getAll()
}