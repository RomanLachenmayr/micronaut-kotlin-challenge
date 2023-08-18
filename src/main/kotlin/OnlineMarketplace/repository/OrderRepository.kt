package OnlineMarketplace.repository

import OnlineMarketplace.model.Order
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface OrderRepository : CrudRepository<Order, String> {
}