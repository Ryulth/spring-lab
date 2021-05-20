package com.ryulth.springlab.config

import org.hibernate.reactive.mutiny.Mutiny
import org.hibernate.reactive.provider.ReactivePersistenceProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory

@Configuration
@EnableTransactionManagement
class HibernateConfig(
    private val environment: Environment
) {

    @Bean
    fun sessionFactory(entityManagerFactory: EntityManagerFactory): Mutiny.SessionFactory {
        return entityManagerFactory.unwrap(Mutiny.SessionFactory::class.java)
    }

    @Bean
    fun entityManagerFactoryBean(): LocalContainerEntityManagerFactoryBean {
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.setPersistenceProviderClass(ReactivePersistenceProvider::class.java)
        entityManagerFactoryBean.setPackagesToScan("com.ryulth.springlab.model")
        entityManagerFactoryBean.setJpaProperties(hibernateProperties())
        return entityManagerFactoryBean
    }

//    No implementation for ReactiveTransactionManager hibernate yet
//    @Bean
//    fun hibernateTransactionManager(entityManagerFactory: EntityManagerFactory): ReactiveTransactionManager {
//        val transactionManager = HibernateTransactionManager()
//        transactionManager.sessionFactory = entityManagerFactory.unwrap(SessionFactory::class.java)
//        return transactionManager
//    }

    private fun hibernateProperties(): Properties {
        val hibernateProperties = Properties()
        hibernateProperties["javax.persistence.jdbc.driver"] = environment.getProperty("javax.persistence.jdbc.driver")
        hibernateProperties["javax.persistence.jdbc.url"] = environment.getProperty("javax.persistence.jdbc.url")
        hibernateProperties["javax.persistence.jdbc.user"] = environment.getProperty("javax.persistence.jdbc.user")
        hibernateProperties["javax.persistence.jdbc.password"] = environment.getProperty("javax.persistence.jdbc.password")
        hibernateProperties["hibernate.hbm2ddl.auto"] = environment.getProperty("hibernate.hbm2ddl.auto")
        hibernateProperties["hibernate.dialect"] = environment.getProperty("hibernate.dialect")
        hibernateProperties["hibernate.show_sql"] = environment.getProperty("hibernate.show_sql")
        hibernateProperties["hibernate.format_sql"] = environment.getProperty("hibernate.format_sql")
        return hibernateProperties
    }

    companion object
}