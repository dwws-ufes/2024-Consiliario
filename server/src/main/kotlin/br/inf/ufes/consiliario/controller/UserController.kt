package br.inf.ufes.consiliario.controller

import br.inf.ufes.consiliario.application.UserApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController("/user")
class UserController(
    val userApplication: UserApplication
) {
    @GetMapping
    suspend fun getUserByEmail(
        @RequestParam email: String
    ) = userApplication.getUserByEmail(email)

    @PostMapping("/register")
    suspend fun createUser() = userApplication.createUser()
}