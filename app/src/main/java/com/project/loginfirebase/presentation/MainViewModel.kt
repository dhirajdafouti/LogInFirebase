package com.project.loginfirebase.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.loginfirebase.domain.usecases.EmailValidate
import com.project.loginfirebase.domain.usecases.ValidatePassword
import com.project.loginfirebase.domain.usecases.ValidateRepeatPassword
import com.project.loginfirebase.domain.usecases.ValidateTerms
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class MainViewModel(
    private val emailValidate: EmailValidate = EmailValidate(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatPassword: ValidateRepeatPassword = ValidateRepeatPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms(),
) : ViewModel() {

    var state by mutableStateOf(RegistrationFormStatus())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegistrationFormEvent.AcceptTerms -> {
                state = state.copy(acceptedTerms = event.isAcceptTerms)
            }
            is RegistrationFormEvent.RepeatPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatPassword)
            }
            is RegistrationFormEvent.Submit -> {
                submitData()
            }

        }

    }


    private fun submitData() {
        val emailResult = emailValidate.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatPasswordResult =
            validateRepeatPassword.execute(state.password, state.repeatedPassword)
        val termsResult = validateTerms.execute(state.acceptedTerms)
        val hasError = listOf(emailResult, passwordResult, repeatPasswordResult, termsResult).any {
            !it.successFull
        }
        if (hasError) {
            state = state.copy(emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                repeatedPasswordError = repeatPasswordResult.errorMessage,
                termsError = termsResult.errorMessage
            )
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    fun clearValues() {
        RegistrationFormStatus("", "", "", "", "")
    }


    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}