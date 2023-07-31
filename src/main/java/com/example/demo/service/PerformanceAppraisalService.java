package com.example.demo.service;

import com.example.demo.entity.PerformanceAppraisal;
import com.example.demo.repository.PerformanceAppraisalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PerformanceAppraisalService {
    @Autowired
    private PerformanceAppraisalRepository appraisalRepository;

    public PerformanceAppraisal savePerformanceAppraisal(PerformanceAppraisal appraisal) {
        return appraisalRepository.save(appraisal);
    }

    public PerformanceAppraisal updatePerformanceAppraisal(Long id, PerformanceAppraisal updatedAppraisal) {
        PerformanceAppraisal existingAppraisal = appraisalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Performance appraisal not found with id: " + id));

        // Update the existing appraisal with the new values
        existingAppraisal.setStatus(updatedAppraisal.getStatus());
        existingAppraisal.setComments(updatedAppraisal.getComments());
        existingAppraisal.setEmployee(updatedAppraisal.getEmployee());

        return appraisalRepository.save(existingAppraisal);
    }

    // Other methods for retrieving appraisals, etc.
}
