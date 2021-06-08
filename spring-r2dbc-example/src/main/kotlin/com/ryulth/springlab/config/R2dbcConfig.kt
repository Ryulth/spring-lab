package com.ryulth.springlab.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.data.relational.core.mapping.NamingStrategy
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.transaction.ReactiveTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement


@Configuration
@EnableR2dbcRepositories
@EnableTransactionManagement
class R2dbcConfig {
    @Bean
    fun namingStrategy(): NamingStrategy {
        return object : NamingStrategy {
            override fun getColumnName(property: RelationalPersistentProperty): String = property.name
        }
    }

//    @Bean
//    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager {
//        return R2dbcTransactionManager(connectionFactory)
//    }
}