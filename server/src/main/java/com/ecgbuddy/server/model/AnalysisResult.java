package com.ecgbuddy.server.model;

import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "analysis_results")
public class AnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name = "analysis_date")
    private LocalDateTime analysisDate;
    
    @Column(name = "heart_rhythm")
    private String heartRhythm;
    
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> biomarkers;
    
    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getAnalysisDate() {
        return analysisDate;
    }

    public void setAnalysisDate(LocalDateTime analysisDate) {
        this.analysisDate = analysisDate;
    }

    public String getHeartRhythm() {
        return heartRhythm;
    }

    public void setHeartRhythm(String heartRhythm) {
        this.heartRhythm = heartRhythm;
    }

    public Map<String, Object> getBiomarkers() {
        return biomarkers;
    }

    public void setBiomarkers(Map<String, Object> biomarkers) {
        this.biomarkers = biomarkers;
    }
}
