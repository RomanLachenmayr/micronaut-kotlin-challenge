package OnlineMarketplace.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive


@MappedEntity
data class Order(

    @field:Id
    @field:GeneratedValue
    val id: String? = null,

    @field:NotBlank(message = "the ordered product must have an id")
    val productId: String,

    @field:Positive(message = "at least one product must be ordered")
    val quantity: Int
)
