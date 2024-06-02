package br.inf.ufes.consiliario.application

import br.inf.ufes.consiliario.dto.StudentRegisterDto
import br.inf.ufes.consiliario.repository.StudentRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class StudentApplication(
    private val studentRepository: StudentRepository
) {
    suspend fun registerStudent(studentRegisterDto: StudentRegisterDto) =
        studentRepository.save(studentRegisterDto.toEntity())

    suspend fun getAllFromAdvisor(advisorId: UUID) = studentRepository.findByAdvisor(advisorId)
}