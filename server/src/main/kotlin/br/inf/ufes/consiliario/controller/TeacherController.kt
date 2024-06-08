package br.inf.ufes.consiliario.controller

import br.inf.ufes.consiliario.application.TeacherApplication
import br.inf.ufes.consiliario.dto.TeacherLoginDto
import br.inf.ufes.consiliario.dto.TeacherRecoverPasswordDto
import br.inf.ufes.consiliario.dto.TeacherRegisterDto
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class TeacherController(
    val teacherApplication: TeacherApplication
) {
    @GetMapping("/teacher")
    suspend fun getTeacher(
        @RequestParam teacherId: UUID
    ) = teacherApplication.getTeacher(teacherId)

    @PostMapping("/register")
    suspend fun registerTeacher(
        @RequestBody teacherRegisterDto: TeacherRegisterDto
    ) = teacherApplication.createTeacher(teacherRegisterDto)

    @PostMapping("/login")
    suspend fun loginTeacher(
        @RequestBody teacherLoginDto: TeacherLoginDto
    ) = teacherApplication.login(teacherLoginDto)

    @PostMapping("/recoverPassword")
    suspend fun recoverPassword(
        @RequestBody teacherRecoverPasswordDto: TeacherRecoverPasswordDto
    ) = teacherApplication.recoverPassword(teacherRecoverPasswordDto)
}