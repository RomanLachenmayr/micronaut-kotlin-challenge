package OnlineMarketplace.controller

import OnlineMarketplace.model.Product
import OnlineMarketplace.service.ProductService
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
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

    @Get("/{product_id}")
    fun getProductById(@PathVariable product_id: String): Product = productService.getProductById(product_id)

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addProduct(@Body product: Product): Product = productService.addProduct(product)

    @Put("/{product_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateProduct(@PathVariable product_id: String, @Body product: Product): Product =
        productService.updateProduct(product_id, product)

    @Delete("/{product_id}")
    fun deleteProduct(@PathVariable product_id: String): String = productService.removeProductById(product_id)

    @Delete("/") // only for testing purposes
    fun deleteAllProducts(): String = productService.deleteAllProducts()
}