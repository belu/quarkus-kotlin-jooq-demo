package me.belu.rest

import me.belu.service.AuthorsService
import me.belu.service.dto.Author
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody
import javax.inject.Inject
import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status.BAD_REQUEST
import javax.ws.rs.core.UriInfo

@Path("authors")
class AuthorsResource {

  @Inject
  lateinit var service: AuthorsService

  @Context
  lateinit var uri: UriInfo

  @POST
  fun addAuthor(@QueryParam("firstName") firstName: String, @QueryParam("lastName") lastName: String): Response {
    val author = service.addAuthor(firstName, lastName)

    return Response.ok(author).location(locationFor(author)).build()
  }

  @GET
  @Path("{authorId}")
  fun getAuthor(@PathParam("authorId") authorId: Int): Response {
    val author = service.getAuthor(authorId) ?: return Response.status(Response.Status.NOT_FOUND).build()

    return Response.ok(author).build()
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  fun getAuthors(): List<Author> {
    return service.getAuthors()
  }

  @PUT
  @Path("{authorId}")
  @Consumes(MediaType.APPLICATION_JSON)
  fun updateAuthor(@PathParam("authorId") authorId: Int, @RequestBody author: Author): Response {
    if (authorId != author.id) {
      return Response.status(BAD_REQUEST)
        .entity("Author ID from path does not match with author ID from JSON body.").build()
    }

    service.updateAuthor(author)

    return Response.noContent().build()
  }

  @PATCH
  @Path("fragile")
  @Produces(MediaType.TEXT_PLAIN)
  fun fragileOperation(): Response {
    val author = service.fragileOperation()

    return Response.ok("Successfully added ${author.firstName} ${author.lastName}").build()
  }

  private fun locationFor(author: Author) = uri.absolutePathBuilder.path(author.id.toString()).build()
}