package sia.tacocloud.repository.jdbc

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import sia.tacocloud.domain.Ingredient
import sia.tacocloud.domain.Taco


@Repository
class JdbcJdbcTacoRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
) : JdbcTacoRepository {

    override fun save(taco: Taco): Taco {
        val tacoId = saveTacoInfo(taco)
        for (ingredient in taco.ingredients) {
            saveIngredientToTaco(ingredient, tacoId)
        }

        return taco
    }

    private fun saveTacoInfo(taco: Taco): Long {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            SQL_INSERT_TACO,
            mapOf(
                with(taco) {
                    "name" to name
                    "createdAt" to createdAt
                }
            ),
            keyHolder,
            arrayOf("id"),
        )

        return keyHolder.key as Long
    }

    private fun saveIngredientToTaco(ingredient: Ingredient, tacoId: Long) {
        jdbcTemplate.update(
            SQL_INSERT_TACO_INGREDIENTS,
            mapOf(
                "taco" to tacoId,
                "ingredient" to ingredient.id,
            )
        )
    }

    private companion object {
        const val SQL_INSERT_TACO = "insert into taco (name, createdAt) values (:name, :createdAt)"

        const val SQL_INSERT_TACO_INGREDIENTS = "insert into Taco_Ingredients (taco, ingredient) " +
                "values (:taco, :ingredient)"
    }
}