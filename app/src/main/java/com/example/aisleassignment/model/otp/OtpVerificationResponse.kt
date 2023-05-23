package com.example.aisleassignment.model.otp

import com.google.gson.annotations.SerializedName

data class OtpVerificationResponse(
    @SerializedName("token") val token: String
)