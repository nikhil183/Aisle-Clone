package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class ProfileDataList(
    @SerializedName("question") var question: String? = null,
    @SerializedName("preferences") var preferences: ArrayList<Preferences> = arrayListOf(),
    @SerializedName("invitation_type") var invitationType: String? = null
)