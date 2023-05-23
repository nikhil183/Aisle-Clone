package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class IndustryV1(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("preference_only") var preferenceOnly: Boolean? = null
)