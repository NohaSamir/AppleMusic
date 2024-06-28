package com.vama.data.util

import com.vama.domain.model.Result
import java.io.IOException

suspend fun <T> runSafeCatching(block: suspend () -> T): Result<T> {
    return runCatching {
        block()
    }.mapCatching {
        Result.Success(it)
    }.getOrElse {
        when (it) {
            is IOException -> Result.Error(it)
            else -> Result.Error(Exception(it.message, it))
        }
    }
}