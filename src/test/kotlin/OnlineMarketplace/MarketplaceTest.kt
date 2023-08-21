package OnlineMarketplace

import OnlineMarketplace.model.Product
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.runtime.server.EmbeddedServer
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.Order

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MarketplaceTest {

    @Inject
    private lateinit var embeddedServer: EmbeddedServer

    @Client("/")
    private lateinit var client: HttpClient

    private lateinit var product: Product
    private lateinit var product2: Product


    @BeforeAll
    fun setup() {
        client = embeddedServer.applicationContext.createBean(HttpClient::class.java, embeddedServer.url)
        client.toBlocking().retrieve(HttpRequest.DELETE<Product>("/products"))
        client.toBlocking().retrieve(HttpRequest.DELETE<OnlineMarketplace.model.Order>("/orders"))

        val request = HttpRequest.
        POST("/products", mapOf("name" to "Barbie-Puppe", "description" to "ab 3 Jahren", "price" to 14.99))
        product = client.toBlocking().exchange(request, Product::class.java).body()

        val request2 = HttpRequest.
        POST("/products", mapOf("name" to "Spielzeut-Auto", "description" to "ab 6 Jahren", "price" to 7.99))
        product2 = client.toBlocking().exchange(request2, Product::class.java).body()
    }

    @AfterAll
    fun tearDown() {
        client.close()
    }



    @Test
    @Order(1)
    fun testGetAllProducts() {
        val request = HttpRequest.GET<Product>("/products")
        val response = client.toBlocking().exchange(request, Argument.listOf(Product::class.java))

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(2, response.body().size)
    }

    @Test
    @Order(2)
    fun testGetProductById() {
        val request = HttpRequest.GET<Product>("/products/${product.id}")
        val response = client.toBlocking().exchange(request, Product::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(product, response.body())
    }

    @Test
    @Order(3)
    fun testUpdateProduct() {
        val request = HttpRequest.PUT("/products/${product.id}",
            mapOf("name" to "Barbie-Puppe", "description" to "ab 3 Jahren", "price" to 10.99))
        val response = client.toBlocking().exchange(request, Product::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals("Barbie-Puppe", response.body().name)
        assertEquals("ab 3 Jahren", response.body().description)
        assertEquals(10.99, response.body().price)

        product = response.body()
    }

    @Test
    @Order(4)
    fun testAddProduct() {
        val request = HttpRequest.
        POST("/products", mapOf("name" to "Lego-Set", "description" to "ab 8 Jahren", "price" to 9.99))
        val response = client.toBlocking().exchange(request, Product::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals("Lego-Set", response.body().name)
        assertEquals("ab 8 Jahren", response.body().description)
        assertEquals(9.99, response.body().price)

        val request2 = HttpRequest.GET<Product>("/products")
        val response2 = client.toBlocking().exchange(request2, Argument.listOf(Product::class.java))

        assertEquals(HttpStatus.OK, response2.status)
        assertEquals(3, response2.body().size)
    }

    @Test
    @Order(5) // this test does not work independently of the previous test
    fun testRemoveProductById() {
        val request = HttpRequest.DELETE<Product>("/products/${product.id}")
        val response = client.toBlocking().exchange(request, String::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals("the product with id ${product.id} was successfully deleted", response.body())

        val request2 = HttpRequest.GET<Product>("/products")
        val response2 = client.toBlocking().exchange(request2, Argument.listOf(Product::class.java))

        assertEquals(HttpStatus.OK, response2.status)
        // change the expected parameter to 1 if you want to run this test independently of the previous one
        assertEquals(2, response2.body().size)
    }

    @Test
    @Order(6)
    fun testGetProductByIdNotExistingProduct() {
        val request = HttpRequest.GET<Product>("/products/123456789123456789123456")
        val response = assertThrows<HttpClientResponseException>
            { client.toBlocking().exchange(request, Product::class.java) }

        assertEquals(HttpStatus.NOT_FOUND, response.status)
    }

    @Test
    @Order(7)
    fun testGetProductByIdInvalidId() {
        val request = HttpRequest.GET<Product>("/products/1234")
        val response = assertThrows<HttpClientResponseException>
        { client.toBlocking().exchange(request, Product::class.java) }

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
    }

    @Test
    @Order(8)
    fun testAddProductInvalidParameters() {
        val request = HttpRequest.
        POST("/products", mapOf("name" to "Playmobil-Set", "description" to "", "price" to 19.99))
        val response = assertThrows<HttpClientResponseException> {
            client.toBlocking().exchange(request, Product::class.java)
        }

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
    }

    @Test
    @Order(9)
    fun testPlaceOrder() {
        val request = HttpRequest.POST("/orders", mapOf("productId" to product2.id, "quantity" to 2))
        val response = client.toBlocking().exchange(request, OnlineMarketplace.model.Order::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(product2.id, response.body().productId)
        assertEquals(2, response.body().quantity)

        val request2 = HttpRequest.GET<Product>("/orders")
        val response2 =
            client.toBlocking().exchange(request2, Argument.listOf(OnlineMarketplace.model.Order::class.java))

        assertEquals(HttpStatus.OK, response2.status)
        assertEquals(1, response2.body().size)
    }

    @Test
    @Order(10)
    fun testPlaceOrderInvalidQuantity() {
        val request = HttpRequest.POST("/orders", mapOf("productId" to product2.id, "quantity" to 0))
        val response = assertThrows<HttpClientResponseException> {
            client.toBlocking().exchange(request, OnlineMarketplace.model.Order::class.java)
        }

        assertEquals(HttpStatus.BAD_REQUEST, response.status)
    }

    @Test
    @Order(11)
    fun testPlaceOrderNotExistingProduct() {
        val request = HttpRequest.
            POST("/orders", mapOf("productId" to "123456789123456789123456", "quantity" to 1))
        val response = assertThrows<HttpClientResponseException> {
            client.toBlocking().exchange(request, OnlineMarketplace.model.Order::class.java)
        }

        assertEquals(HttpStatus.NOT_FOUND, response.status)
    }

}