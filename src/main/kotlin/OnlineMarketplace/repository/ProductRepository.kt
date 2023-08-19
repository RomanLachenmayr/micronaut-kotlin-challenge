package OnlineMarketplace.repository

import OnlineMarketplace.model.Product
import io.micronaut.data.annotation.Repository
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository

@MongoRepository
interface ProductRepository : CrudRepository<Product, String> {
}