package com.example.children_ms.dto

import java.time.LocalDate
import java.time.LocalDateTime

data class ChildrenWithEvaluationsDto (
    val id: Long? = null,
    val fullName: String? = null,
    val nui: String? = null,
    val birthdate: LocalDate? = null,
    val gender: String? = null,
    val creationDate: LocalDateTime? = null,
    val evaluations: List<EvaluationsDto> = emptyList()
)