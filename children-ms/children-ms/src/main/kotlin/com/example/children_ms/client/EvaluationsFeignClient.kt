package com.example.children_ms.client

import com.example.children_ms.dto.EvaluationsDto
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "EVALUATIONS-MS", url = "\${external.evaluations.ms.base-url}")

interface EvaluationsFeignClient {
    @GetMapping("/evaluations")
    fun getEvaluations(): List<EvaluationsDto>
}