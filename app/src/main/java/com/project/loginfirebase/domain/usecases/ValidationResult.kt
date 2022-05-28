package com.project.loginfirebase.domain.usecases

data class ValidationResult(
    val successFull: Boolean = false,
    val errorMessage: String? = null,
)
