package com.accessercise.android.data.model

import com.google.gson.annotations.SerializedName

data class ServerErrorApiModel(
    @SerializedName("error_code") val errorCode: String,
    @SerializedName("error_message") val message: String?,
    @SerializedName("errors") val errors: List<ServerErrorDetailApiModel> = listOf(),
)