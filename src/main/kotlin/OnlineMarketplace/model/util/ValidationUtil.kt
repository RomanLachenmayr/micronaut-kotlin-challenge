package OnlineMarketplace.model.util

import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException

class ValidationUtil {

    companion object {

        fun validateProductParameters(name: String, description: String, price: Double) {
            if (name.isEmpty()) {
                throw HttpStatusException(HttpStatus.BAD_REQUEST, "the product must have a name")
            }
            if (description.isEmpty()) {
                throw HttpStatusException(HttpStatus.BAD_REQUEST, "the product must have a description")
            }
            if (price <= 0.0) {
                throw HttpStatusException(HttpStatus.BAD_REQUEST, "the product must have a price greater than 0€")
            }
        }

        fun validateOrderParameters(productId: String, quantity: Int) {
            validateId(productId)
            if(quantity <= 0) {
                throw HttpStatusException(HttpStatus.BAD_REQUEST, "at least one product must be ordered")
            }

        }

        fun validateId(id: String) {
            val hexRegex = Regex("[0-9A-Fa-f]+")
            if (id.length != 24 || !hexRegex.matches(id)) {
                throw HttpStatusException(HttpStatus.BAD_REQUEST, "the provided id is not valid")
            }
        }
    }

}