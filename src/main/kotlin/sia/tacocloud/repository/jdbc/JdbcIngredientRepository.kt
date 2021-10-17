package sia.tacocloud.repository.jdbc

import sia.tacocloud.domain.Ingredient

interface JdbcIngredientRepository {

    fun findAll(): Iterable<Ingredient>

    fun findById(id: String): Ingredient?

    fun save(ingredient: Ingredient)
}