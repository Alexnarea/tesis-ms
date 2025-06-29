package com.example.children_ms.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalDateTime

class ChildrenDto {

    var id: Long? = null

    @field:NotNull(message = "fullName is required")
    @field:NotBlank(message = "fullName cannot be blank")
    var fullName: String? = null

    @field:NotNull(message = "nui is required")
    @field:NotBlank(message = "nui cannot be blank")
    var nui: String? = null

    @field:NotNull(message = "birthdate is required")
    var birthdate: LocalDate? = null

    var gender: String? = null

    var creationDate: LocalDateTime? = null
}