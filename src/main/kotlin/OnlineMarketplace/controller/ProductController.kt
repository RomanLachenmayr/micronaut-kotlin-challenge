package OnlineMarketplace.controller

import OnlineMarketplace.model.Product
import OnlineMarketplace.service.ProductService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Put

@Controller("/products")
class ProductController (private val productService: ProductService) {

    @Get("/")
    fun getAllProducts(): List<Product> = productService.getAllProducts()

    @Get("/{id}")
    fun getProductById(@PathVariable id: String): Product = productService.getProductById(id)

    @Post("/")
    fun addProduct(@Body product: Product): Product = productService.addProduct(product)

    @Put("/{id}")
    fun updateProduct(@PathVariable id: String, @Body product: Product) = productService.updateProduct(id, product)

    @Delete("/{id}")
    fun deleteProduct(@PathVariable id: String) = productService.removeProductById(id)

}