package com.asif.githubfinder.android.ui.userdetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asif.githubfinder.android.ui.common.Event
import com.asif.githubfinder.common.Logger
import com.asif.githubfinder.domain.models.User
import com.asif.githubfinder.domain.usecases.GetUserUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class UserDetailsViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logger: Logger,
    private val ioDispatcher: CoroutineDispatcher,
    private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val user = MutableLiveData<User>()

    val shouldShowLoadingIndicator = MutableLiveData<Boolean>()

    val openBrowserEvent = MutableLiveData<Event<String>> ()

    val showErrorToastEvent = MutableLiveData<Event<Unit>>()

    val closeActivityEvent = MutableLiveData<Event<Unit>>()

    fun load(username: String) = viewModelScope.launch(mainDispatcher) {
        shouldShowLoadingIndicator.value = true

        launch(ioDispatcher) {
            try {
                val retrievedUser = getUserUseCase(username)

                logger.debug("retrievedUser: $retrievedUser")

                launch(mainDispatcher) {
                    shouldShowLoadingIndicator.value = false
                    user.value = retrievedUser
                }
            } catch (e: Exception) {
                logger.warn("An error occurred while getting user details", e)

                launch(mainDispatcher) {
                    showErrorToastEvent.value = Event(Unit)
                    closeActivityEvent.value = Event(Unit)
                }
            }
        }
    }

    fun onBlogClick() = viewModelScope.launch(mainDispatcher) {
        user.value?.blog?.let { blog ->
            openBrowserEvent.value = Event(blog)
        }
    }
}