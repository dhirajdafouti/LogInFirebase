package com.project.loginfirebase.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.project.loginfirebase.domain.usecases.EmailValidate
import com.project.loginfirebase.domain.usecases.ValidatePassword
import com.project.loginfirebase.domain.usecases.ValidateRepeatPassword
import com.project.loginfirebase.domain.usecases.ValidateTerms


class MainViewModel(
    private val emailValidate: EmailValidate = EmailValidate(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatPassword: ValidateRepeatPassword = ValidateRepeatPassword(),
    private val validateTerms: ValidateTerms = ValidateTerms(),
) : ViewModel() {

    private var _state = mutableStateOf(RegistrationFormStatus())
    val state: State<RegistrationFormStatus> = _state
}