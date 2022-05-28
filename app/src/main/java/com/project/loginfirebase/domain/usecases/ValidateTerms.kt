package com.project.loginfirebase.domain.usecases

import com.project.loginfirebase.domain.utils.ValidationResult

class ValidateTerms {
    fun execute(terms: Boolean): ValidationResult {
        if (!terms) {
            ValidationResult(
                false, "Please accept the validation results"
            )
        }
        return ValidationResult(
            true
        )
    }

}