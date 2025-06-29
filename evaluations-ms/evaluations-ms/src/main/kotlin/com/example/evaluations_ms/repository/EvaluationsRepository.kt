package com.example.evaluations_ms.repository

import com.example.evaluations_ms.entity.Evaluations
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EvaluationsRepository: JpaRepository<Evaluations, Long> {
}