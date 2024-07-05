package com.vama.domain.exceptions

data class NetworkException(val errorMessage: String) : Exception(errorMessage)

data class CachedDataException(val errorMessage: String) : Exception(errorMessage)