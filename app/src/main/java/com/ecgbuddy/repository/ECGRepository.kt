package com.ecgbuddy.repository

import com.ecgbuddy.data.api.ApiService
import com.ecgbuddy.data.local.ECGDao
import com.ecgbuddy.data.model.ECGImage
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class ECGRepository @Inject constructor(
    private val apiService: ApiService,
    private val ecgDao: ECGDao
) {
    suspend fun uploadECGImage(userId: Int, imageFile: File): Result<ECGImage> {
        return try {
            val requestFile = RequestBody.create(
                "image/*".toMediaTypeOrNull(),
                imageFile
            )
            val body = MultipartBody.Part.createFormData(
                "image",
                imageFile.name,
                requestFile
            )
            val response = apiService.uploadECG(body, userId.toString().toRequestBody())
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Upload failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
