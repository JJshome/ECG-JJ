package com.ecgbuddy.data.api

import com.ecgbuddy.data.model.ECGImage
import com.ecgbuddy.data.model.AnalysisResult
import com.ecgbuddy.data.model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
    
    @Multipart
    @POST("uploadECG")
    suspend fun uploadECG(
        @Part image: MultipartBody.Part,
        @Part("userId") userId: RequestBody
    ): Response<ECGImage>
    
    @GET("users/{userId}/analysis")
    suspend fun getAnalysisResults(@Path("userId") userId: Int): List<AnalysisResult>
}
