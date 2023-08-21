package OnlineMarketplace.service

import OnlineMarketplace.model.Product
import OnlineMarketplace.model.util.ValidationUtil
import OnlineMarketplace.repository.ProductRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton

@Singleton
class ProductService (private val productRepository: ProductRepository) {


    fun getAllProducts(): List<Product> = productRepository.findAll().toList()


    fun getProductById(id: String): Product {
        ValidationUtil.validateId(id)
        if (!productRepository.existsById(id)) {
            throw HttpStatusException(HttpStatus.NOT_FOUND, "Product with Id $id does not exist")
        }
        return productRepository.findById(id).get()
    }


    fun addProduct(product: Product): Product {
        ValidationUtil.validateProductParameters(product.name, product.description, product.price)
        return productRepository.save(product)
    }


    fun updateProduct(id: String, updatedProduct: Product): Product {
        ValidationUtil.validateId(id)
        if (!productRepository.existsById(id)) {
            throw HttpStatusException(HttpStatus.NOT_FOUND,
                "Product with Id $id could not be updated as it does not exist")
        }
        ValidationUtil.validateProductParameters(updatedProduct.name, updatedProduct.description, updatedProduct.price)

        val productToUpdate = productRepository.findById(id).get()
        productToUpdate.description = updatedProduct.description
        productToUpdate.name = updatedProduct.name
        productToUpdate.price = updatedProduct.price
        return productRepository.update(productToUpdate)
    }


    fun removeProductById(id: String): String {
        ValidationUtil.validateId(id)
        if (!productRepository.existsById(id)) {
            throw HttpStatusException(HttpStatus.NOT_FOUND,
                "Product with id $id cannot be deleted as it does not exist")
        }
        productRepository.deleteById(id)
        return "the product with id $id was successfully deleted"
    }

    fun deleteAllProducts(): String { // only for testing purposes
        productRepository.deleteAll()
        return "all products were successfully deleted"
    }


}
