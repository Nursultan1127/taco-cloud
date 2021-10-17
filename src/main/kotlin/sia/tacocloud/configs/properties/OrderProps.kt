package sia.tacocloud.configs.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Configuration
@ConfigurationProperties(prefix = "taco.orders")
@Validated
data class OrderProps(
    @field:Min(value = 5, message = "must be between 5 and 25")
    @field:Max(value = 25, message = "must be between 5 and 25")
    val pageSize: Int,
)