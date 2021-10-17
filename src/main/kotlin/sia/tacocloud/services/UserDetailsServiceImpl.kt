package sia.tacocloud.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import sia.tacocloud.repository.jpa.JpaUserRepository

@Service
class UserDetailsServiceImpl(
    private val userRepository: JpaUserRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByUsername(username)
            ?: throw UsernameNotFoundException("User with username: [$username] does not exist")
}