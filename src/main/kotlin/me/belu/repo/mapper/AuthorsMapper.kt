package me.belu.repo.mapper

import me.belu.gen.tables.records.AuthorRecord
import me.belu.service.dto.Author

fun AuthorRecord.mapToDto(): Author {
  return Author(this.id, this.firstName, this.lastName)
}

fun List<AuthorRecord>.mapToDto(): List<Author> {
  return this.map { it.mapToDto() }
}