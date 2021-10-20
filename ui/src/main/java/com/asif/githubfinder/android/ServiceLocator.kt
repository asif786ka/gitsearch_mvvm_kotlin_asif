package com.asif.githubfinder.android

import com.asif.githubfinder.common.Logger
import com.asif.githubfinder.domain.interfaces.GithubDataSource
import com.asif.githubfinder.domain.interfaces.SearchHistoryDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.reflect.KClass

interface ServiceLocator {

    val githubDataSource: GithubDataSource

    val searchHistoryDataSource: SearchHistoryDataSource

    val ioDispatcher: CoroutineDispatcher

    val mainDispatcher: CoroutineDispatcher

    fun getLogger(cls: KClass<*>): Logger
}