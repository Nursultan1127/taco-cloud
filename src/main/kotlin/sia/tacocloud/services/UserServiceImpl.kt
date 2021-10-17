package sia.tacocloud.services

import mu.KLogging
import org.springframework.stereotype.Service
import sia.tacocloud.domain.User
import sia.tacocloud.repository.jpa.JpaUserRepository

@Service
class UserServiceImpl(
    private val userRepository: JpaUserRepository,
) : UserService {

    override fun save(user: User): User {
        logger.info { "Saving user: [$user] " }

        return userRepository.save(user)
            .also { logger.info { "User: [$user] has been successfully saved" } }
    }

    private companion object : KLogging()
}