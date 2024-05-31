package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.dto.TeacherLoginDto
import br.inf.ufes.consiliario.dto.TeacherLoginResponseDto
import br.inf.ufes.consiliario.dto.TeacherRegisterDto
import br.inf.ufes.consiliario.entity.Teacher
import br.inf.ufes.consiliario.repository.TeacherRepository
import br.inf.ufes.consiliario.security.JWTUtil
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class TeacherApplication(
    private val teacherRepository: TeacherRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtUtil: JWTUtil
) {
    suspend fun createTeacher(
        teacherRegisterDto: TeacherRegisterDto
    ) {
        val encryptedPassword = passwordEncoder.encode(teacherRegisterDto.password)

        teacherRepository.save(
            Teacher(
                email = teacherRegisterDto.email,
                username = teacherRegisterDto.username,
                password = encryptedPassword
            )
        )
    }

    suspend fun login(teacherLoginDto: TeacherLoginDto): TeacherLoginResponseDto {
        val teacher = teacherRepository.findByEmail(teacherLoginDto.email)
            ?: throw BadCredentialsException("Invalid credentials")

        if (passwordEncoder.matches(teacherLoginDto.password, teacher.password))
            return TeacherLoginResponseDto(jwtUtil.generateToken(teacher))
        else throw BadCredentialsException("Invalid credentials")
    }
}