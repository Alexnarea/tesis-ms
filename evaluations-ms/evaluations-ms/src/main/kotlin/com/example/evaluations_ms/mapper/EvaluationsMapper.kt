package com.example.evaluations_ms.mapper

import com.example.evaluations_ms.dto.EvaluationsDto
import com.example.evaluations_ms.entity.Evaluations
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
object EvaluationsMapper {

    fun toEntity(evaluationsDto: EvaluationsDto): Evaluations {
        val evaluations = Evaluations()
        evaluations.id = evaluationsDto.id
        //evaluations.applicationDate = evaluationsDto.applicationDate
        evaluations.chronologicalAgeMonths = evaluationsDto.chronologicalAgeMonths
        evaluations.childrenId = evaluationsDto.childrenId
        evaluations.evaluatorId = evaluationsDto.evaluatorId
        return evaluations
    }

    fun toEvaluationsDto(evaluations: Evaluations): EvaluationsDto {
        val evaluationsDto = EvaluationsDto()
        evaluationsDto.id = evaluations.id
        evaluationsDto.applicationDate = evaluations.applicationDate
        evaluationsDto.chronologicalAgeMonths = evaluations.chronologicalAgeMonths
        evaluationsDto.childrenId = evaluations.childrenId
        evaluationsDto.evaluatorId = evaluations.evaluatorId
        return evaluationsDto
    }
}