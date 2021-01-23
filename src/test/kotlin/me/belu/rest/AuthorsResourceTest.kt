package me.belu.rest

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class AuthorsResourceTest {

  @Test
  fun getAuthors_returnsEmptyList() {
    given()
      .`when`().get("/authors")
      .then()
      .statusCode(200)
      .body(`is`("[]"))
  }
}