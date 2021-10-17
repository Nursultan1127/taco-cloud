package sia.tacocloud.repository.jdbc

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.simple.SimpleJdbcInsert
import org.springframework.stereotype.Repository
import sia.tacocloud.domain.Order
import sia.tacocloud.domain.Taco


@Repository
class JdbcOrderRepositoryImpl(
    private val jdbcTemplate: JdbcTemplate,
    private val objectMapper: ObjectMapper,
) : JdbcOrderRepository {

    private val orderInserter = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("Taco_Order")
        .usingGeneratedKeyColumns("id")

    private val orderTacoInserter = SimpleJdbcInsert(jdbcTemplate)
        .withTableName("Taco_Order_Tacos")

    override fun save(order: Order): Order {
        val orderId = saveOrderDetails(order)
        val savedOrder = order.copy(id = orderId)

        for (taco in order.tacos) {
            saveTacoToOrder(taco, orderId)
        }

        return order
    }

    @Suppress("UNCHECKED_CAST")
    private fun saveOrderDetails(order: Order): Long {
        val values: MutableMap<String, Any> =
            objectMapper.convertValue(order, MutableMap::class.java) as MutableMap<String, Any>

        return orderInserter.executeAndReturnKey(values)
            .toLong()
    }

    private fun saveTacoToOrder(taco: Taco, orderId: Long) {
        val values: MutableMap<String, Any> = HashMap()
        values["tacoOrder"] = orderId
        values["taco"] = taco.id

        orderTacoInserter.execute(values)
    }
}