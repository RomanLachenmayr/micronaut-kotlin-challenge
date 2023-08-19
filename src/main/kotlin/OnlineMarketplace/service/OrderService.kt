package OnlineMarketplace.service

import OnlineMarketplace.model.Order
import OnlineMarketplace.repository.OrderRepository
import jakarta.inject.Singleton

@Singleton
class OrderService (private val orderRepository: OrderRepository) {

    fun placeOrder(order: Order): Order = orderRepository.save(order)


}