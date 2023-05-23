package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class Profiles(
    @SerializedName("first_name") var firstName: String? = null,
    @SerializedName("avatar") var avatar: String? = null
)