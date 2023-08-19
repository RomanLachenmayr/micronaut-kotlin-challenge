package OnlineMarketplace.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity


@MappedEntity
data class Order(

    @field:Id
    @field:GeneratedValue
    val id: String? = null,

    //@field:NotBlank(message = "the ordered product must have an id")
    val productId: String,

    //@field:Positive(message = "at least one product must be ordered")
    val quantity: Int

)
