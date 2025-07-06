package com.example.children_ms.service

import com.example.children_ms.client.EvaluationsFeignClient
import com.example.children_ms.dto.ChildrenDto
import com.example.children_ms.dto.ChildrenWithEvaluationsDto
import com.example.children_ms.mapper.ChildrenMapper
import com.example.children_ms.repository.ChildrenRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ChildrenService {

    @Autowired
    lateinit var evaluationsFeignClient: EvaluationsFeignClient

    @Autowired
    lateinit var childrenRepository: ChildrenRepository
    @Autowired
    lateinit var childrenMapper: ChildrenMapper

    fun findAll(): List<ChildrenDto> {
        val children = childrenRepository.findAll()
        return children.map {childrenMapper.toChildrenDto(it)}
    }

    fun findById(id: Long): ChildrenDto {
        val children = childrenRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Children with ID $id not found") }
        return childrenMapper.toChildrenDto(children)
    }

    fun findAllWithEvaluations(): List<ChildrenWithEvaluationsDto> {
        val childrenList = childrenRepository.findAll()
        val evaluationsList = evaluationsFeignClient.getEvaluations()

        return childrenList.map { child ->
            val childEvaluations = evaluationsList.filter { it.childrenId == child.id }
            ChildrenWithEvaluationsDto(
                id = child.id,
                fullName = child.fullName,
                nui = child.nui,
                birthdate = child.birthdate,
                gender = child.gender,
                creationDate = child.creationDate,
                evaluations = childEvaluations
            )
        }
    }


    fun save(childrenDto: ChildrenDto): ChildrenDto {
        //childrenDto.creationDate = LocalDateTime.now()
        val children = childrenMapper.toEntity(childrenDto)
        val saveChildren = childrenRepository.save(children)
        return childrenMapper.toChildrenDto(saveChildren)
    }

    fun update(id: Long, childrenDto: ChildrenDto): ChildrenDto {
        val children = childrenRepository.findById(id)
            .orElseThrow { EntityNotFoundException("Children with ID $id not found") }
        children.apply {
            fullName = childrenDto.fullName
            nui = childrenDto.nui
            birthdate = childrenDto.birthdate
            gender = childrenDto.gender
        }
        val updateChildren = childrenRepository.save(children)
        return childrenMapper.toChildrenDto(updateChildren)
    }

    fun delete(id: Long) {
        val children = childrenRepository.findById(id)
            .orElseThrow{ EntityNotFoundException("Children with ID $id not found") }
        childrenRepository.delete(children)
    }
}