/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package br.giovannicuriel.kafka.listener

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class App {
}
fun main(args: Array<String>) {
    runApplication<App>()
}
