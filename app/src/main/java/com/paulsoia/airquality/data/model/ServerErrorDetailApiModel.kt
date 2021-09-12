package com.accessercise.android.data.model

import com.google.gson.annotations.SerializedName

data class ServerErrorDetailApiModel(
    @SerializedName("field") val field: String,
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
)
