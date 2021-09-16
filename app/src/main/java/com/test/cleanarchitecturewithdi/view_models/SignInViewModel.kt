package com.test.cleanarchitecturewithdi.view_models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.cleanarchitecturewithdi.domains.SignInUseCase
import com.test.cleanarchitecturewithdi.models.SignIn
import com.test.cleanarchitecturewithdi.domains.Result
import com.test.cleanarchitecturewithdi.domains.Status
import com.test.cleanarchitecturewithdi.utils.WhileViewSubscribed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val _navigationActions = Channel<SignInNavigationAction>(Channel.CONFLATED)
    val navigationActions = _navigationActions.receiveAsFlow()

    private val _message = Channel<String>(Channel.CONFLATED)
    val message = _message.receiveAsFlow()

    private val _isLoggedIn = Channel<Boolean>(Channel.CONFLATED)
    var isLoggedIn = _isLoggedIn.receiveAsFlow()

    var demoMutableState = MutableStateFlow(false)   // It's like mutable live data

    private val userAuth = MutableSharedFlow<SignIn>()

    private val loginData = userAuth.flatMapLatest {
        signInUseCase(it)
    }.stateIn(
        scope = viewModelScope,
        started = WhileViewSubscribed,
        initialValue = Result.nothing()
    )

    init {
        viewModelScope.launch {
            loginData.collect {
                if (it.status == Status.ERROR) {
                    _message.send(it.message.toString())
                } else if (it.status == Status.SUCCESS) {
//                    _isLoggedIn.send(true)
                    _navigationActions.send(SignInNavigationAction.NavigateToHome)
                }
            }
        }
    }

    fun signIn() {
        val userName = "admin"
        val password = "Test@123"

        viewModelScope.launch {
            userAuth.emit(SignIn(userName, password))
        }
    }
}

sealed class SignInNavigationAction {
    object NavigateToHome : SignInNavigationAction()
}
