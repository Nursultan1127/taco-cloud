package sia.tacocloud.services

import sia.tacocloud.domain.User

interface UserService {

    fun save(user: User): User
}