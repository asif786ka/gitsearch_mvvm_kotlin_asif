package com.asif.githubfinder.domain.interfaces

import com.asif.githubfinder.domain.models.SearchHistoryItem

interface SearchHistoryDataSource {

    fun getAll(): List<SearchHistoryItem>

    fun add(searchHistoryItem: SearchHistoryItem)
}