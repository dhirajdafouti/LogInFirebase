package com.project.loginfirebase.presentation

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatPasswordChanged(val repeatPassword: String) : RegistrationFormEvent()
    data class AcceptTerms(val isAcceptTerms: Boolean) : RegistrationFormEvent()
    object Submit : RegistrationFormEvent()
}