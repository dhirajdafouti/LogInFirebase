package com.project.loginfirebase.domain.usecases

class ValidateTerms {
    fun execute(terms: Boolean): ValidationResult {
        if (!terms) {
            ValidationResult(
                false, "Please accept the validation results!!!"
            )
        }
        return ValidationResult(
            true
        )
    }

}