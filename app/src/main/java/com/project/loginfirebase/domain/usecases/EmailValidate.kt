package com.project.loginfirebase.domain.usecases

import com.project.loginfirebase.domain.utils.Extension.checkValidEmailId

class EmailValidate {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(
                false,
                "Email cannot be blank!!!"
            )
        }
        if (!checkValidEmailId(email)) {
            return ValidationResult(false,
                "This is not a valid EmailId!!!")
        }
        return ValidationResult(
            true
        )
    }
}