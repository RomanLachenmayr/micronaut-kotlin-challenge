package OnlineMarketplace.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive


@MappedEntity
data class Product(

    @field:Id
    @field:GeneratedValue
    val id: String? = null,

    @field:NotBlank(message = "the product must have a name")
    var name: String,

    @field:NotBlank(message = "the product must have a description")
    var description: String,

    @field:Positive(message = "the product must have a price greater than 0€")
    var price: Double
)


