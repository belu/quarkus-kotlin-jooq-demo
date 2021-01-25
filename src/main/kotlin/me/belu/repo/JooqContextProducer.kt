package me.belu.repo

import io.agroal.api.AgroalDataSource
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.jboss.logging.Logger
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import javax.enterprise.context.Dependent
import javax.enterprise.inject.Produces
import javax.inject.Inject

@Dependent
class JooqContextProducer {

  @Inject
  lateinit var log: Logger

  @Inject
  lateinit var dataSource: AgroalDataSource

  @ConfigProperty(name = "quarkus.jooq.dialect", defaultValue = "DEFAULT")
  lateinit var confDialect: String

  @Produces
  fun dslContext(): DSLContext {
    val dialect = SQLDialect.valueOf(confDialect)

    log.info("jOOQ using dialect: $dialect")

    return DSL.using(dataSource, dialect)
  }
}