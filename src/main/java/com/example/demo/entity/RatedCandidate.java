package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "RATED_CANDIDATE")
public class RatedCandidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;
    @ManyToOne(targetEntity = PerformanceAppraisal.class)
    @JoinColumn(name = "performance_appraisal_id")
    private PerformanceAppraisal performanceAppraisal;
    @ManyToOne(targetEntity = Rating.class)
    @JoinColumn(name = "rating_id")
    private Rating rating;
    @ManyToOne(targetEntity = Evaluation.class)
    @JoinColumn(name = "evaluation_id")
    private Evaluation evaluation;
}
