package com.vama.domain.model

sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val e: Exception) : Result<Nothing>()
}