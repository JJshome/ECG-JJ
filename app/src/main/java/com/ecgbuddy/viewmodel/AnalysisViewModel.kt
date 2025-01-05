package com.ecgbuddy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ecgbuddy.data.model.AnalysisResult
import com.ecgbuddy.repository.ECGRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnalysisViewModel @Inject constructor(
    private val repository: ECGRepository,
    private val pdfGenerator: PDFGenerator
) : ViewModel() {
    private val _analysisResult = MutableStateFlow<AnalysisResult?>(null)
    val analysisResult = _analysisResult.asStateFlow()
    
    fun loadAnalysisResult(userId: Int) {
        viewModelScope.launch {
            try {
                val result = repository.getAnalysisResults(userId).firstOrNull()
                _analysisResult.value = result
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
    
    fun generatePDF(result: AnalysisResult) {
        viewModelScope.launch {
            try {
                val pdfFile = pdfGenerator.generateReport(result)
                // Handle PDF generation success
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
