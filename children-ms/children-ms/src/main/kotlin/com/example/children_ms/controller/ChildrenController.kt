package com.example.children_ms.controller

import com.example.children_ms.dto.ChildrenDto
import com.example.children_ms.dto.ChildrenWithEvaluationsDto
import com.example.children_ms.response.SuccessResponse
import com.example.children_ms.service.ChildrenService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/children")
class ChildrenController {
    @Autowired
    lateinit var childrenService: ChildrenService


    @GetMapping
    fun findAllChildren(): ResponseEntity<*> {
        val response = childrenService.findAll()
        return ResponseEntity(SuccessResponse(data = response), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<*> {
        val response = childrenService.findById(id)
        return ResponseEntity(SuccessResponse(data = response), HttpStatus.OK)
    }

    @GetMapping("/with-evaluations")
    fun findAllWithEvaluations(): ResponseEntity<List<ChildrenWithEvaluationsDto>> {
        val result = childrenService.findAllWithEvaluations()
        return ResponseEntity.ok(result)
    }

    @PostMapping
    fun save (@RequestBody @Valid childrenDto: ChildrenDto): ResponseEntity<Any> {
        val response = childrenService.save(childrenDto)
        return ResponseEntity(SuccessResponse(data = response), HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody @Valid childrenDto: ChildrenDto): ResponseEntity<Any> {
        val response = childrenService.update(id, childrenDto)
        return ResponseEntity(SuccessResponse(data = response), HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Any> {
        val response = childrenService.delete(id)
        return ResponseEntity(SuccessResponse(data = response), HttpStatus.OK)
    }
}