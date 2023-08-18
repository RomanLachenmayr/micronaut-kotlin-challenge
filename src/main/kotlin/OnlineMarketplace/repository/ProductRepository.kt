package OnlineMarketplace.repository

import OnlineMarketplace.model.Product
import io.micronaut.data.annotation.Repository
import io.micronaut.data.repository.CrudRepository

@Repository
interface ProductRepository : CrudRepository<Product, String> {
}