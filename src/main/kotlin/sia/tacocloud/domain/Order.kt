package sia.tacocloud.domain

import org.hibernate.validator.constraints.CreditCardNumber
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Digits
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
@Table(name = "Taco_Order")
data class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @field:NotBlank(message = "Name is required")
    val name: String,
    @field:NotBlank(message = "Street is required")
    val street: String,
    @field:NotBlank(message = "City is required")
    val city: String,
    @field:NotBlank(message = "State is required")
    val state: String,
    @field:NotBlank(message = "Zip code is required")
    val zip: String,
    @CreditCardNumber(message = "Not a valid credit card number")
    val ccNumber: String,
    @field:Pattern(
        regexp = VALID_CC_EXPIRATION_REGEX,
        message = "Must be formatted MM/YY"
    )
    val ccExpiration: String,
    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    val ccVV: String,
    val placedAt: LocalDateTime = LocalDateTime.now(),
    val tacos: List<Taco>,
    @ManyToOne
    val user: User,
) {

    private companion object {
        const val VALID_CC_EXPIRATION_REGEX = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$"
    }
}
