package OnlineMarketplace.service
import OnlineMarketplace.model.Order
import OnlineMarketplace.model.Product
import OnlineMarketplace.repository.OrderRepository
import io.micronaut.core.annotation.NonNull
import jakarta.inject.Singleton

@Singleton
class OrderService (private val orderRepository: OrderRepository) {


    fun placeOrder(order: Order): Order {
        return orderRepository.save(order)
    }
}