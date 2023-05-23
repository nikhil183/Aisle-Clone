package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class FieldOfStudyV1(
    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)