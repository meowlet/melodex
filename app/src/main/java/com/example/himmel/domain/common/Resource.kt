package com.example.himmel.domain.common

sealed class Resource<out T> {
    // Initial state
    object Loading : Resource<Nothing>()

    // Success state with data
    data class Success<T>(
        val data: T,
        val message: String? = null
    ) : Resource<T>()

    // Error state with message and optional data
    data class Error(
        val message: String,
        val errorCode: Int? = null,
        val throwable: Throwable? = null
    ) : Resource<Nothing>()

    // Empty state when there is no data
    object Empty : Resource<Nothing>()

    // Helper functions
    fun isLoading() = this is Loading
    fun isSuccess() = this is Success
    fun isError() = this is Error
    fun isEmpty() = this is Empty

    // Get data safely
    fun data(): T? = when (this) {
        is Success -> data
        else -> null
    }
}

