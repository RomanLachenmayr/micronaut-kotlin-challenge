package OnlineMarketplace.service

import OnlineMarketplace.model.Order
import OnlineMarketplace.model.util.ValidationUtil
import OnlineMarketplace.repository.OrderRepository
import jakarta.inject.Singleton

@Singleton
class OrderService (private val orderRepository: OrderRepository) {

    fun placeOrder(order: Order): Order {
        ValidationUtil.validateOrderParameters(order.productId, order.quantity)
        return orderRepository.save(order)
    }


}