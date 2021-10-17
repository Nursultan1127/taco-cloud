package sia.tacocloud.repository.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sia.tacocloud.domain.Order

@Repository
interface JpaOrderRepository : CrudRepository<Order, Long>