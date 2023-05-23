package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("summary") var summary: String? = null,
    @SerializedName("full") var full: String? = null
)