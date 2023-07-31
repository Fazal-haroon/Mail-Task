package com.example.demo.controller;

import com.example.demo.entity.PerformanceAppraisal;
import com.example.demo.service.PerformanceAppraisalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/performance-appraisals")
public class PerformanceAppraisalController {
    @Autowired
    private PerformanceAppraisalService appraisalService;

    @PostMapping
    public ResponseEntity<PerformanceAppraisal> createPerformanceAppraisal(@RequestBody PerformanceAppraisal appraisal) {
        PerformanceAppraisal savedAppraisal = appraisalService.savePerformanceAppraisal(appraisal);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAppraisal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerformanceAppraisal> updatePerformanceAppraisal(@PathVariable Long id, @RequestBody PerformanceAppraisal updatedAppraisal) {
        PerformanceAppraisal appraisal = appraisalService.updatePerformanceAppraisal(id, updatedAppraisal);
        return ResponseEntity.ok(appraisal);
    }

    // Other endpoints for retrieving appraisals, etc.
}
