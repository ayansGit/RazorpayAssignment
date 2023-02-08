package com.ayan.razorpayassignment.models

import com.google.gson.annotations.SerializedName

data class UiResponse(

    @SerializedName("logo-url") val logoUrl : String,
    @SerializedName("heading-text") val headingText: String,
    @SerializedName("uidata") val uidata: ArrayList<UiData>
)
