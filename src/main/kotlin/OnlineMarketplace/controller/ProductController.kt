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
import io.micronaut.validation.Validated
import jakarta.validation.Valid

@Validated
@Controller("/products")
class ProductController (private val productService: ProductService) {

    @Get("/")
    fun getAllProducts(): List<Product> = productService.getAllProducts()

    @Get("/{product_id}")
    fun getProductById(@PathVariable product_id: String): Product = productService.getProductById(product_id)

    @Post("/")
    @Consumes(MediaType.APPLICATION_JSON)
    fun addProduct(@Body @Valid product: Product): Product = productService.addProduct(product)

    @Put("/{product_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun updateProduct(@PathVariable product_id: String, @Body @Valid product: Product) =
        productService.updateProduct(product_id, product)

    @Delete("/{product_id}")
    fun deleteProduct(@PathVariable product_id: String) = productService.removeProductById(product_id)

}