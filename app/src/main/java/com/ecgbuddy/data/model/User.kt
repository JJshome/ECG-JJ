package com.ecgbuddy.data.model

data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val gender: String,
    val medicalHistory: String
)

data class ECGImage(
    val id: Int,
    val userId: Int,
    val imagePath: String,
    val capturedAt: String
)

data class AnalysisResult(
    val id: Int,
    val userId: Int,
    val analysisDate: String,
    val heartRhythm: String,
    val biomarkers: Map<String, Any>
)
