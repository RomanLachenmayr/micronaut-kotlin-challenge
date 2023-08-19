package OnlineMarketplace.service

import OnlineMarketplace.model.Product
import OnlineMarketplace.repository.ProductRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton

@Singleton
class ProductService (private val productRepository: ProductRepository) {


    fun getAllProducts(): List<Product> = productRepository.findAll().toList()


    fun getProductById(id: String): Product {
        if (!productRepository.existsById(id)) {
            throw HttpStatusException(HttpStatus.NOT_FOUND, "Product with Id $id does not exist")
        }
        return productRepository.findById(id).get()
    }


    fun addProduct(product: Product): Product = productRepository.save(product)


    fun updateProduct(id: String, updatedProduct: Product): Product {
        if (!productRepository.existsById(id)) {
            throw HttpStatusException(HttpStatus.NOT_FOUND,
                "Product with Id $id could not be updated as it does not exist")
        }
        val productToUpdate = productRepository.findById(id).get()
        productToUpdate.description = updatedProduct.description
        productToUpdate.name = updatedProduct.name
        productToUpdate.price = updatedProduct.price
        return productRepository.save(productToUpdate)
    }


    fun removeProductById(id: String) {
        if (!productRepository.existsById(id)) {
            throw HttpStatusException(HttpStatus.NOT_FOUND,
                "Product with Id $id could not be deleted as it does not exist")
        }
        productRepository.deleteById(id)
    }


}
