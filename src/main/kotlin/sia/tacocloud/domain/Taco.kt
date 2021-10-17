package sia.tacocloud.domain

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Size

@Entity
data class Taco(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @field:Size(min = 5, message = "Name must be at least 5 characters long")
    val name: String,
    @ManyToMany(targetEntity = Ingredient::class)
    @field:Size(min = 1, message = "You must choose at least 1 ingredient")
    val ingredients: List<Ingredient>,
    var createdAt: LocalDateTime,
) {
    @PrePersist
    private fun createdAt() {
        this.createdAt = LocalDateTime.now()
    }
}