package sia.tacocloud.repository.jdbc

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import sia.tacocloud.domain.Ingredient
import java.sql.ResultSet

@Repository
class JdbcIngredientRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
) : JdbcIngredientRepository {

    override fun findAll(): Iterable<Ingredient> =
        jdbcTemplate.query(
            SQL_FIND_ALL,
            ROW_MAPPER,
        )

    override fun findById(id: String): Ingredient? =
        jdbcTemplate.queryForObject(
            SQL_FIND_BY_ID,
            ROW_MAPPER,
        )

    override fun save(ingredient: Ingredient) {
        jdbcTemplate.update(
            "insert into ingredients (id, name, type) values (:id, :name, :type)",
            mapOf(
                with(ingredient) {
                    "id" to id
                    "name" to name
                    "type" to type.name
                }
            )
        )
    }

    private companion object {
        const val SQL_FIND_ALL = "select * from ingredients"

        const val SQL_FIND_BY_ID = "$SQL_FIND_ALL where id = :id"

        val ROW_MAPPER = RowMapper { rs: ResultSet, _: Int ->
            Ingredient(
                id = rs.getString("id"),
                name = rs.getString("name"),
                type = Ingredient.Type.valueOf(rs.getString("type"))
            )
        }
    }
}