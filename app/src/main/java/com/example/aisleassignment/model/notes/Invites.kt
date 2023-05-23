package com.example.aisleassignment.model.notes

import com.google.gson.annotations.SerializedName

data class Invites(
    @SerializedName("profiles") var profiles: ArrayList<InvitesProfile> = arrayListOf(),
    @SerializedName("totalPages") var totalPages: Int? = null,
    @SerializedName("pending_invitations_count") var pendingInvitationsCount: Int? = null
)