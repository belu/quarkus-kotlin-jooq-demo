package me.belu.service

import me.belu.repo.AuthorsRepo
import me.belu.repo.mapper.mapToDto
import me.belu.service.dto.Author
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.transaction.Transactional.TxType.NEVER
import kotlin.random.Random

@ApplicationScoped
@Transactional
class AuthorsService() {

  @Inject
  lateinit var repo: AuthorsRepo

  fun addAuthor(firstName: String, lastName: String): Author {
    return repo.addAuthor(firstName, lastName).into(Author::class.java) // mapping via jOOQ into()
  }

  @Transactional(NEVER)
  fun getAuthor(authorId: Int): Author? {
    return repo.getAuthor(authorId)?.mapToDto() // mapping via extension function
  }

  @Transactional(NEVER)
  fun getAuthors(): List<Author> {
    return repo.getAuthors().mapToDto()
  }

  fun updateAuthor(author: Author) {
    repo.updateAuthor(author)
  }

  fun fragileOperation(): Author {
    val firstName = "Hans"
    val lastName = "the " + Random.nextInt(20, 100) + "th"
    println("Adding author: $firstName $lastName")

    val authorRecord = repo.addAuthor(firstName, lastName)

    if (Random.nextBoolean()) {
      throw UnsupportedOperationException("Sh*t happens. Unable to add $firstName $lastName.")
    }

    return authorRecord.mapToDto()
  }
}