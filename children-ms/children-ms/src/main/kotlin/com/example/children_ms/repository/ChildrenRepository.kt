package com.example.children_ms.repository

import com.example.children_ms.entity.Children
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChildrenRepository: JpaRepository<Children, Long> {
}