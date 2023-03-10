package com.ayan.razorpayassignment.models

import com.google.gson.annotations.SerializedName

data class UiData(
    @SerializedName("uitype") val uitype: String,
    @SerializedName("value") val value: String,
    @SerializedName("key") val key: String,
    @SerializedName("hint") val hint: String,

)
