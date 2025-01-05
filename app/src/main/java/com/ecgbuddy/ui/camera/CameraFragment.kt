package com.ecgbuddy.ui.camera

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CameraFragment : Fragment() {
    private val viewModel: CameraViewModel by viewModels()
    private lateinit var cameraController: LifecycleCameraController
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupCamera()
        setupUI()
    }
    
    private fun setupCamera() {
        cameraController = LifecycleCameraController(requireContext())
        cameraController.bindToLifecycle(viewLifecycleOwner)
        viewBinding.previewView.controller = cameraController
        
        cameraController.setImageCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
    }
    
    private fun captureImage() {
        val photoFile = createTempImageFile()
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
        
        cameraController.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    viewModel.uploadECGImage(photoFile)
                }
                
                override fun onError(exception: ImageCaptureException) {
                    // Handle error
                }
            }
        )
    }
}
