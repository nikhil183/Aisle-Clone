package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class ProfilePreferences(
    @SerializedName("answer_id") var answerId: Int? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("value") var value: Int? = null,
    @SerializedName("preference_question") var preferenceQuestion: PreferenceQuestion? = PreferenceQuestion(),
)