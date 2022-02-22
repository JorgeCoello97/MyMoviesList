package com.jcoello.domain.util

import java.io.Serializable

sealed class Results<T>(
    var data: T? = null,
    val message: String? = null
) : Serializable {
    class Success<T>(data: T) : Results<T>(data = data)
    class Loading<T>: Results<T>()
    class Error<T>(message: String): Results<T>(message = message)
}