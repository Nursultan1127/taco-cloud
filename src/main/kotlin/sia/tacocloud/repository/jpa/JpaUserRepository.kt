package sia.tacocloud.repository.jpa

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sia.tacocloud.domain.User

@Repository
interface JpaUserRepository : CrudRepository<User, Long> {

    fun findByUsername(username: String): User?
}