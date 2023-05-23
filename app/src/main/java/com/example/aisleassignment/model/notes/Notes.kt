package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class Notes(
    @SerializedName("invites") var invites: Invites? = Invites(),
    @SerializedName("likes") var likes: Likes? = Likes()
)