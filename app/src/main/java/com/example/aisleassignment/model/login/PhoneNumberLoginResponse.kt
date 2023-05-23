package com.example.aisleassignment.model.login

import com.google.gson.annotations.SerializedName

data class PhoneNumberLoginResponse(
    @SerializedName("status") val status: Boolean
)