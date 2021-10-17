package sia.tacocloud.repository.jdbc

import sia.tacocloud.domain.Taco

interface JdbcTacoRepository {

    fun save(taco: Taco): Taco
}