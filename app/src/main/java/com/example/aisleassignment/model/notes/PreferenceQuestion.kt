package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class PreferenceQuestion(
    @SerializedName("first_choice") var firstChoice: String? = null,
    @SerializedName("second_choice") var secondChoice: String? = null
)