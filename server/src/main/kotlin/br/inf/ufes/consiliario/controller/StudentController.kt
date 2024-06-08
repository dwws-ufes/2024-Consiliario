package br.inf.ufes.consiliario.controller

import br.inf.ufes.consiliario.application.StudentApplication
import br.inf.ufes.consiliario.dto.StudentRegisterDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/student")
class StudentController(
    private val studentApplication: StudentApplication
) {
    @GetMapping
    suspend fun getStudent(
        @RequestParam studentId: UUID
    ) = studentApplication.getStudent(studentId)

    @PostMapping
    suspend fun registerStudent(
        @RequestBody studentRegisterDto: StudentRegisterDto
    ) = studentApplication.registerStudent(studentRegisterDto)

    @GetMapping("/from-advisor")
    suspend fun getAllFromAdvisor(
        @RequestParam advisorId: UUID
    ) = studentApplication.getAllFromAdvisor(advisorId)
}