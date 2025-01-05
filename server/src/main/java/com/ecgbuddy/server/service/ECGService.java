package com.ecgbuddy.server.service;

import com.ecgbuddy.server.model.ECGImage;
import com.ecgbuddy.server.model.AnalysisResult;
import com.ecgbuddy.server.repository.ECGImageRepository;
import com.ecgbuddy.server.repository.AnalysisResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ECGService {
    private final ECGImageRepository ecgImageRepository;
    private final AnalysisResultRepository analysisResultRepository;
    private final FileStorageService fileStorageService;
    
    @Autowired
    public ECGService(
        ECGImageRepository ecgImageRepository,
        AnalysisResultRepository analysisResultRepository,
        FileStorageService fileStorageService
    ) {
        this.ecgImageRepository = ecgImageRepository;
        this.analysisResultRepository = analysisResultRepository;
        this.fileStorageService = fileStorageService;
    }
    
    public ECGImage saveECGImage(MultipartFile file, Long userId) throws IOException {
        String imagePath = fileStorageService.storeFile(file);
        
        ECGImage ecgImage = new ECGImage();
        ecgImage.setUser(userRepository.findById(userId).orElseThrow());
        ecgImage.setImagePath(imagePath);
        ecgImage.setCapturedAt(LocalDateTime.now());
        
        return ecgImageRepository.save(ecgImage);
    }
    
    public AnalysisResult analyzeECG(Long imageId) {
        ECGImage image = ecgImageRepository.findById(imageId).orElseThrow();
        
        // Perform ECG analysis here
        Map<String, Object> biomarkers = performAnalysis(image);
        
        AnalysisResult result = new AnalysisResult();
        result.setUser(image.getUser());
        result.setAnalysisDate(LocalDateTime.now());
        result.setBiomarkers(biomarkers);
        
        return analysisResultRepository.save(result);
    }
    
    private Map<String, Object> performAnalysis(ECGImage image) {
        // Implement ECG analysis logic
        return new HashMap<>();
    }
}
