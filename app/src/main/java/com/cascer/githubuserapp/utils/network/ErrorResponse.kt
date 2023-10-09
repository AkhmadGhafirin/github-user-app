package com.cascer.githubuserapp.utils.network

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code") var code: Int?,
    @SerializedName("status_message") val message: String?
) {
    override fun toString(): String {
        return "ErrorResponse(code=$code, message=$message)"
    }
}