package com.example.demo.repository;

import com.example.demo.entity.RatedCandidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatedCandidateRepository extends JpaRepository<RatedCandidate, Long> {
}
