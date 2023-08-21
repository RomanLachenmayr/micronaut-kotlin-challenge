package OnlineMarketplace.controller

import OnlineMarketplace.model.Order
import OnlineMarketplace.service.OrderService
import OnlineMarketplace.service.ProductService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*

@Controller("/orders")
class OrderController (
    private val orderService: OrderService,
    private val productService: ProductService
    ) {

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    fun placeOrder(@Body order: Order): Order {
        productService.getProductById(order.productId) // verify existence of product
        return orderService.placeOrder(order)
    }

    @Delete("/") // only for testing purposes
    fun deleteAllOrders(): String = orderService.deleteAllOrders()

    @Get("/") // only for testing purposes
    fun getAllOrders(): List<Order> = orderService.getAllOrders()

}