package OnlineMarketplace.repository

import OnlineMarketplace.model.Order
import io.micronaut.data.annotation.Repository
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface OrderRepository : CrudRepository<Order, String> {
}