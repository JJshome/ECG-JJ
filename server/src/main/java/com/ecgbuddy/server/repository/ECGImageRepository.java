package com.ecgbuddy.server.repository;

import com.ecgbuddy.server.model.ECGImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ECGImageRepository extends JpaRepository<ECGImage, Long> {
    List<ECGImage> findByUserId(Long userId);
}
