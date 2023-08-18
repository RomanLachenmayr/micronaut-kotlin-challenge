package OnlineMarketplace.controller

import OnlineMarketplace.model.Order
import OnlineMarketplace.model.Product
import OnlineMarketplace.service.OrderService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post

@Controller("/orders")
class OrderController (private val orderService: OrderService) {

    @Post("/")
    fun placeOrder(@Body order: Order): Order = orderService.placeOrder(order)

}