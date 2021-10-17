package sia.tacocloud.domain

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

data class RegistrationForm(
    val username: String,
    val password: String,
    val fullName: String,
    val street: String,
    val city: String,
    val state: String,
    val zip: String,
    val phoneNumber: String,
)

fun RegistrationForm.toUser(passwordEncoder: PasswordEncoder): UserDetails =
    User(
        username = username,
        password = passwordEncoder.encode(password),
        fullName = fullName,
        street = street,
        city = city,
        state = state,
        zip = zip,
        phoneNumber = phoneNumber,
    )
