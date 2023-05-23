package com.example.aisleassignment.model.login

import com.google.gson.annotations.SerializedName

data class PhoneNumberLoginInput(
    @SerializedName("number") val number: String
)