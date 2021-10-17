package sia.tacocloud.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val userDetailsService: UserDetailsService,
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        /*auth
            .inMemoryAuthentication()
            .withUser("nursd")
            .password("123")
            .authorities("ROLE_USER")*/
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/design", "orders").hasRole("ROLE_USER")
            .antMatchers("/register").anonymous()
            .and().formLogin().loginPage("/login").defaultSuccessUrl("/design")
            .and().logout().logoutSuccessUrl("/")
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder =
        BCryptPasswordEncoder()
}