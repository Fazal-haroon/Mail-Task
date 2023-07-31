package com.example.demo.model;

import com.example.demo.entity.Employee;
import com.example.demo.entity.RatedCandidate;
import lombok.Data;

import java.util.Set;

@Data
public class PerformanceAppraisalRequest {
    private String status;
    private String comments;
    private Employee employee;
    private Set<RatedCandidate> ratedCandidates;
}

