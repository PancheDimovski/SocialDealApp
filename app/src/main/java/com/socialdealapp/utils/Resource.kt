package com.socialdealapp.utils

sealed class Resource<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Failure<T>(error: String) : Resource<T>(error = error)
    data class Loading<T>(val isLoading: Boolean = true) : Resource<T>()
}