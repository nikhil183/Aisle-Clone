package com.example.aisleassignment.model.otp

import com.google.gson.annotations.SerializedName

data class OtpVerificationInput(
    @SerializedName("number") val number: String,
    @SerializedName("otp") val otp: String
)