package com.example.children_ms.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "children")
class Children {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    var id: Long? = null

    @Column(name = "full_name", nullable = false)
    var fullName: String? = null

    @Column(nullable = false, unique = true)
    var nui: String? = null

    @Column(nullable = false)
    var birthdate: LocalDate? = null

    var gender: String? = null

    @Column(name = "creation_date", insertable = false, updatable = false)
    var creationDate: LocalDateTime? = null
}
