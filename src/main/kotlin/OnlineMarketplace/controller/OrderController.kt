package OnlineMarketplace.controller

import OnlineMarketplace.model.Order
import OnlineMarketplace.service.OrderService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.validation.Valid

@Validated
@Controller("/orders")
class OrderController (private val orderService: OrderService) {

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    fun placeOrder(@Body @Valid order: Order): Order = orderService.placeOrder(order)

}