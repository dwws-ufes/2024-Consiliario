package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.dto.TeacherLoginDto
import br.inf.ufes.consiliario.dto.TeacherLoginResponseDto
import br.inf.ufes.consiliario.dto.TeacherRecoverPasswordDto
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
        teacherRepository.save(
            Teacher(
                email = teacherRegisterDto.email,
                username = teacherRegisterDto.username,
                password = passwordEncoder.encode(teacherRegisterDto.password)
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

    suspend fun recoverPassword(teacherRecoverPasswordDto: TeacherRecoverPasswordDto) {
        val teacher = teacherRepository.findByEmail(teacherRecoverPasswordDto.email)
            ?: throw BadCredentialsException("Invalid credentials")

        if (!passwordEncoder.matches(teacherRecoverPasswordDto.oldPassword, teacher.password))
            throw BadCredentialsException("Invalid credentials")

        teacherRepository.save(
            Teacher(
                id = teacher.id,
                email = teacher.email,
                username = teacher.username,
                password = passwordEncoder.encode(teacherRecoverPasswordDto.newPassword)
            )
        )
    }
}