package com.example.children_ms.dto

import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

class EvaluationsDto {
    var id: Long? = null
    var applicationDate: LocalDateTime? = null

    @field:NotNull(message = "chronologicalAgeMonths is required")
    var chronologicalAgeMonths: Int? = null

    @field:NotNull(message = "childrenId is required")
    var childrenId: Long? = null

    @field:NotNull(message = "evaluatorId is required")
    var evaluatorId: Long? = null
}