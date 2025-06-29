package com.example.evaluations_ms.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "evaluations")
class Evaluations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "application_date", insertable = false, updatable = false)
    var applicationDate: LocalDateTime? = null

    @Column(name = "chronological_age_months", nullable = false)
    var chronologicalAgeMonths: Int? = null

    @Column(name = "children_id", nullable = false)
    var childrenId: Long? = null

    @Column(name = "evaluator_id", nullable = false)
    var evaluatorId: Long? = null
}
