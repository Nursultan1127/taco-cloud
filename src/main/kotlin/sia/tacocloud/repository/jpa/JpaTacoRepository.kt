package sia.tacocloud.repository.jpa

import org.springframework.data.repository.CrudRepository
import sia.tacocloud.domain.Taco

interface JpaTacoRepository : CrudRepository<Taco, Long>