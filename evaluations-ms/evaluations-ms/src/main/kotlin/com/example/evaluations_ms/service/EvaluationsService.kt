package com.example.evaluations_ms.service

import com.example.evaluations_ms.dto.EvaluationsDto
import com.example.evaluations_ms.mapper.EvaluationsMapper
import com.example.evaluations_ms.repository.EvaluationsRepository
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EvaluationsService {

    @Autowired
    private lateinit var evaluationsMapper: EvaluationsMapper

    @Autowired
    lateinit var evaluationsRepository: EvaluationsRepository

    //@Autowired
    //lateinit var responsesRepository: ResponsesRepository

    //@Autowired
    //lateinit var globalResultsRepository: GlobalResultsRepository

    //@Autowired
    //lateinit var evaluationsMapper: EvaluationsMapper

    //@Autowired
    //lateinit var responsesService: ResponsesService

    //@Autowired
    //lateinit var testItemsService: TestItemsService

    //@Autowired
    //lateinit var globalResultsService: GlobalResultsService

    fun findAll(): List<EvaluationsDto> {
        return evaluationsRepository.findAll()
            .map { evaluationsMapper.toEvaluationsDto(it) }
    }

    fun findById(id: Long): EvaluationsDto {
        val evaluation = evaluationsRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Evaluation with ID $id not found") }
        return evaluationsMapper.toEvaluationsDto(evaluation)
    }

    /*fun getEvaluationDetail(id: Long): EvaluationDetailDto {
        try {
            val evaluation = evaluationsRepository.findById(id)
                .orElseThrow { EntityNotFoundException("Evaluation with ID $id not found") }

            val globalResult = globalResultsRepository.findByEvaluationId(id)
                .orElseThrow { EntityNotFoundException("Global result not found for evaluation $id") }

            val responses = responsesRepository.findByEvaluationId(id)
            if (responses.isEmpty()) {
                throw EntityNotFoundException("No responses associated with evaluation ID $id")
            }

            val items = responses.map { response ->
                val item = response.item
                    ?: throw EntityNotFoundException("Item not found for response ID ${response.id}")
                EvaluationItemDto(
                    id = item.id ?: throw EntityNotFoundException("Item ID not found"),
                    task = item.description ?: "[No description]",
                    domain = item.domain?.descriptionDomain ?: "[No domain]",
                    completed = response.passed ?: false,
                    referenceAgeMonths = item.referenceAgeMonths ?: 0
                )
            }

            return EvaluationDetailDto(
                id = evaluation.id ?: throw EntityNotFoundException("Evaluation ID not found"),
                applicationDate = evaluation.applicationDate ?: LocalDateTime.now(),
                chronologicalAgeMonths = evaluation.chronologicalAgeMonths ?: 0,
                childrenId = evaluation.childrenId ?: throw EntityNotFoundException("Children ID not found"),
                evaluatorId = evaluation.evaluatorId ?: throw EntityNotFoundException("Evaluator ID not found"),
                resultYears = globalResult.resultYears ?: "[Not calculated]",
                coefficient = globalResult.coefficient?.toDouble() ?: 0.0,
                classification = globalResult.classification ?: "[No classification]",
                observaciones = "",
                items = items
            )

        } catch (e: EntityNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, e.message)
        } catch (e: Exception) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Error getting evaluation detail: ${e.message}"
            )
        }
    }*/

    @Transactional
    fun save(dto: EvaluationsDto): EvaluationsDto {
        val entity = evaluationsMapper.toEntity(dto)
        entity.applicationDate = LocalDateTime.now()
        val saved = evaluationsRepository.save(entity)
        return evaluationsMapper.toEvaluationsDto(saved)
    }

    @Transactional
    fun update(id: Long, dto: EvaluationsDto): EvaluationsDto {
        val entity = evaluationsRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Evaluation with ID $id not found") }

        entity.chronologicalAgeMonths = dto.chronologicalAgeMonths
        entity.childrenId = dto.childrenId
        entity.evaluatorId = dto.evaluatorId
        val updated = evaluationsRepository.save(entity)
        return evaluationsMapper.toEvaluationsDto(updated)
    }

    @Transactional
    fun delete(id: Long) {
        val entity = evaluationsRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Evaluation with ID $id not found") }
        evaluationsRepository.delete(entity)
    }

    /*@Transactional
    fun createEvaluationWithResponses(request: EvaluationRequestDto): EvaluationResultDto {
        println("➡️ Starting creation of evaluation with responses: $request")

        val dto = EvaluationsDto().apply {
            childrenId = request.childrenId
            evaluatorId = request.evaluatorId
            chronologicalAgeMonths = request.chronologicalAgeMonths
        }

        val savedEvaluation = save(dto)
        println("✅ Evaluation saved with ID: ${savedEvaluation.id}")

        request.responses.forEach { resp ->
            println("📝 Saving response for itemId=${resp.itemId}, passed=${resp.passed}")
            val responseDto = ResponsesDto().apply {
                evaluationId = savedEvaluation.id!!
                itemId = resp.itemId
                passed = resp.passed
            }
            responsesService.save(responseDto)
        }

        println("🔄 Calculating global result for evaluation ID: ${savedEvaluation.id}")
        val globalResult = globalResultsService.calculateAndSaveResult(savedEvaluation.id!!)

        println("✅ Global result calculated: $globalResult")

        return EvaluationResultDto(
            evaluationId = savedEvaluation.id!!,
            totalMonthsApproved = globalResult.totalMonthsApproved?.toDouble() ?: 0.0,
            coefficient = globalResult.coefficient?.toDouble() ?: 0.0,
            classification = globalResult.classification ?: "",
            resultYears = globalResult.resultYears ?: "",
            resultDetail = globalResult.resultDetail ?: ""
        )
    }*/
}