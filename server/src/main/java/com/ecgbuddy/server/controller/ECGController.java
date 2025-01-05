package com.ecgbuddy.server.controller;

import com.ecgbuddy.server.model.ECGImage;
import com.ecgbuddy.server.model.AnalysisResult;
import com.ecgbuddy.server.service.ECGService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ECGController {
    private final ECGService ecgService;
    
    @Autowired
    public ECGController(ECGService ecgService) {
        this.ecgService = ecgService;
    }
    
    @PostMapping("/uploadECG")
    public ResponseEntity<ECGImage> uploadECG(
        @RequestParam("file") MultipartFile file,
        @RequestParam("userId") Long userId
    ) {
        try {
            ECGImage savedImage = ecgService.saveECGImage(file, userId);
            return ResponseEntity.ok(savedImage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping("/users/{userId}/analysis")
    public ResponseEntity<List<AnalysisResult>> getAnalysisResults(
        @PathVariable Long userId
    ) {
        List<AnalysisResult> results = ecgService.getAnalysisResults(userId);
        return ResponseEntity.ok(results);
    }
}
