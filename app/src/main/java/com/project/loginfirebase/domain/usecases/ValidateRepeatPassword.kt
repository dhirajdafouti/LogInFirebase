package com.project.loginfirebase.domain.usecases

import com.project.loginfirebase.domain.utils.ValidationResult

class ValidateRepeatPassword {
    fun execute(password: String, repeatPassword: String): ValidationResult {
        if (password != repeatPassword) {
            return ValidationResult(true,
                "PassWord does not match"
            )
        }
        return ValidationResult(
            true
        )
    }
}