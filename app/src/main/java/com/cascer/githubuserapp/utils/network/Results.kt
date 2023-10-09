package com.cascer.githubuserapp.utils.network

sealed class Results<out R> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val exception: Exception) : Results<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

val Results<*>.isSuccessful
    get() = this is Results.Success

val Results<*>.isFailure
    get() = this is Results.Error