package com.example.children_ms

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients
@SpringBootApplication
class ChildrenMsApplication

fun main(args: Array<String>) {
	runApplication<ChildrenMsApplication>(*args)
}
