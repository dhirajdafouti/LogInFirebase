package com.project.loginfirebase.domain.usecases

import com.project.loginfirebase.domain.utils.Extension.hasDigit
import com.project.loginfirebase.domain.utils.Extension.hasLowerCase
import com.project.loginfirebase.domain.utils.Extension.hasUpperCase
import com.project.loginfirebase.domain.utils.Extension.length

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.isBlank()) {
            return ValidationResult(
                false,
                "PassWord Cannot be blank"
            )
        }
        if (!hasDigit(password)) {
            return ValidationResult(false,
                "Password has no single digit")
        }
        if (!hasLowerCase(password)) {
            return ValidationResult(false,
                "Password does not have Lower Case")
        }

        if (!hasUpperCase(password)) {
            return ValidationResult(false,
                "Password does not have Upper Case")
        }
        if (!length(password)) {
            return ValidationResult(false,
                "Password is weak")
        }
        return ValidationResult(
            true, "Password is strong."
        )
    }
}