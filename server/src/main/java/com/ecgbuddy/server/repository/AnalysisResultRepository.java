package com.ecgbuddy.server.repository;

import com.ecgbuddy.server.model.AnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisResultRepository extends JpaRepository<AnalysisResult, Long> {
    List<AnalysisResult> findByUserIdOrderByAnalysisDateDesc(Long userId);
}
