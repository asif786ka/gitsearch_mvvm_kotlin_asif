package com.asif.githubfinder.android.ui.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asif.githubfinder.android.ServiceLocator
import com.asif.githubfinder.android.ui.search.SearchViewModel
import com.asif.githubfinder.android.ui.userdetails.UserDetailsViewModel
import com.asif.githubfinder.domain.usecases.GetSearchHistoryUseCase
import com.asif.githubfinder.domain.usecases.GetUserUseCase
import com.asif.githubfinder.domain.usecases.SearchUsersUseCase

class ViewModelFactory(private val serviceLocator: ServiceLocator) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(SearchViewModel::class.java) ->
                    SearchViewModel(
                        SearchUsersUseCase(
                            serviceLocator.githubDataSource,
                            serviceLocator.searchHistoryDataSource,
                            serviceLocator.getLogger(SearchUsersUseCase::class)
                        ),
                        GetSearchHistoryUseCase(
                            serviceLocator.searchHistoryDataSource
                        ),
                        serviceLocator.getLogger(SearchViewModel::class),
                        serviceLocator.ioDispatcher,
                        serviceLocator.mainDispatcher
                    )
                isAssignableFrom(UserDetailsViewModel::class.java) ->
                    UserDetailsViewModel(
                        GetUserUseCase(
                            serviceLocator.githubDataSource
                        ),
                        serviceLocator.getLogger(UserDetailsViewModel::class),
                        serviceLocator.ioDispatcher,
                        serviceLocator.mainDispatcher
                    )
                else -> throw IllegalArgumentException("unknown model class $modelClass")
            }
        } as T
}