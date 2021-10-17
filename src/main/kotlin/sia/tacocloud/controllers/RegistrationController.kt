package sia.tacocloud.controllers

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import sia.tacocloud.domain.RegistrationForm
import sia.tacocloud.domain.User
import sia.tacocloud.domain.toUser
import sia.tacocloud.services.UserService

@Controller
@RequestMapping("/register")
class RegistrationController(
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder,
) {

    @GetMapping
    fun registerForm(): String =
        "registration"

    @PostMapping
    fun processRegistration(registrationForm: RegistrationForm): String {
        userService.save(registrationForm.toUser(passwordEncoder) as User)

        return "redirect:/login"
    }
}