package com.example.aisleassignment.network

import com.example.aisleassignment.model.otp.OtpVerificationInput
import com.example.aisleassignment.model.otp.OtpVerificationResponse
import com.example.aisleassignment.model.login.PhoneNumberLoginInput
import com.example.aisleassignment.model.login.PhoneNumberLoginResponse
import com.example.aisleassignment.model.notes.Notes
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @POST("/V1/users/phone_number_login")
    suspend fun isNumberRegistered(
        @Body body: PhoneNumberLoginInput
    ): Response<PhoneNumberLoginResponse>

    @POST("/V1/users/verify_otp")
    suspend fun verifyOtp(
        @Body body: OtpVerificationInput
    ): Response<OtpVerificationResponse>

    @GET("/V1/users/test_profile_list")
    suspend fun getNotes(@Header("Authorization") token: String): Response<Notes>
}