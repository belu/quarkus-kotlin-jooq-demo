package me.belu.repo

import me.belu.gen.tables.Author.AUTHOR
import me.belu.gen.tables.records.AuthorRecord
import me.belu.service.dto.Author
import org.jooq.DSLContext
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class AuthorsRepo {

  @Inject
  lateinit var dsl: DSLContext

  fun addAuthor(firstName: String, lastName: String): AuthorRecord {
    val authorRecord = dsl.newRecord(AUTHOR)

    with(authorRecord) {
      this.firstName = firstName
      this.lastName = lastName
      store()
    }

    return authorRecord
  }

  fun getAuthor(authorId: Int): AuthorRecord? {
    return dsl.selectFrom(AUTHOR).where(AUTHOR.ID.eq(authorId)).fetchOne()
  }

  fun getAuthors(): List<AuthorRecord> {
    return dsl.selectFrom(AUTHOR).toList()
  }

  fun updateAuthor(author: Author) {
    // Kotlin style
    val authorRecord = dsl.selectFrom(AUTHOR).where(AUTHOR.ID.eq(author.id)).fetchOne() ?: return
    with(authorRecord) {
      firstName = author.firstName
      lastName = author.lastName
      update()
    }

    // Java style
    /*
    dsl.update(AUTHOR)
     .set(AUTHOR.FIRST_NAME, author.firstName)
     .set(AUTHOR.LAST_NAME, author.lastName)
     .where(AUTHOR.ID.eq(author.id))
     .execute()
     */
  }
}