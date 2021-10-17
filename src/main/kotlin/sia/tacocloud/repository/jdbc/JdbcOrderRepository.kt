package sia.tacocloud.repository.jdbc

import sia.tacocloud.domain.Order

interface JdbcOrderRepository {

    fun save(order: Order): Order
}