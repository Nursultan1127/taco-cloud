package sia.tacocloud.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Ingredient(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: String,
    val name: String,
    val type: Type,
) {
    enum class Type {
        WRAP,
        PROTEIN,
        VEGGIES,
        CHEESE,
        SAUCE;
    }
}