package com.asif.githubfinder.domain.usecases

import com.asif.githubfinder.domain.interfaces.SearchHistoryDataSource
import com.asif.githubfinder.domain.models.SearchHistoryItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class GetSearchHistoryUseCaseTest {

    private lateinit var getSearchHistoryUseCase: GetSearchHistoryUseCase

    @MockK
    private lateinit var searchHistoryDataSource: SearchHistoryDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getSearchHistoryUseCase = GetSearchHistoryUseCase(searchHistoryDataSource)
    }

    @Test
    fun testInvoke() {
        val searchHistory = listOf(
            SearchHistoryItem(id = 1, query = "john"),
            SearchHistoryItem(id = 2, query = "jane")
        )

        every { searchHistoryDataSource.getAll() }.returns(searchHistory)

        assertEquals(searchHistory, getSearchHistoryUseCase())
    }
}