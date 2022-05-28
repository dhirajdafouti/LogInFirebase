package com.project.loginfirebase.domain.utils

import java.util.regex.Matcher
import java.util.regex.Pattern

object Extension {
    val regex: String = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"

    fun checkValidEmailId(emailId: String): Boolean {
        return Pattern.matches(regex, emailId)
    }

    fun hasLowerCase(lowerCase: String): Boolean {
        val pattern: Pattern = Pattern.compile("[a-z]")
        val hasLowerCase: Matcher = pattern.matcher(lowerCase)
        return hasLowerCase.find()
    }

    fun hasUpperCase(upperCase: String): Boolean {
        val pattern: Pattern = Pattern.compile("[A-Z]")
        val hasUpperCase: Matcher = pattern.matcher(upperCase)
        return hasUpperCase.find()
    }

    fun hasDigit(digits: String): Boolean {
        val pattern: Pattern = Pattern.compile("[0-9]")
        val hasDigit: Matcher = pattern.matcher(digits)
        return hasDigit.find()
    }

    fun hasSpecialCharacter(specialCharacter: String): Boolean {
        val pattern: Pattern = Pattern.compile("[!@#$%^&*()+_-{}:|~`]")
        val hasSpecialCharacter: Matcher = pattern.matcher(specialCharacter)
        return hasSpecialCharacter.find()
    }

    fun length(password: String): Boolean {
        return password.length >= 8
    }
}