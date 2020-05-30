package com.youngplussoft.sbdemo

import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager
import org.springframework.orm.jpa.vendor.Database
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = ["com.youngplussoft.sbdemo.jpa.model"])
@EnableJpaRepositories(entityManagerFactoryRef = "jpaEntityManagerFactory", transactionManagerRef = "jpaTransactionManager", basePackages = ["com.youngplussoft.sbdemo.jpa.repository"])
class JpaConfig {
    val dialect = "org.hibernate.dialect.MySQL57Dialect"
    var ddlAuto = "validate"
    var showSql = "true"
    var useNewIdGeneratorMappings = "false"
    var implicitStrategy = "org.hibernate.cfg.ImprovedNamingStrategy"
    var physicalStrategy = "org.hibernate.cfg.ImprovedNamingStrategy"

    @Autowired(required = false)
    private val persistenceUnitManager: PersistenceUnitManager? = null

    @Bean(name = ["jpaDataSource"])
    @ConfigurationProperties(prefix = "spring.jpa-datasource.hikari")
    fun dataSource(): DataSource {
        val dataSource: HikariDataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()
        dataSource.setConnectionInitSql("set @@session.time_zone = '\$TIMEZONE'".replace("\$TIMEZONE", Calendar.getInstance().timeZone.id))
        return dataSource
    }

    @Bean(name = ["jpaEntityManagerFactoryBuilder"])
    fun jpaEntityManagerFactoryBuilder(): EntityManagerFactoryBuilder {
        val adapter = HibernateJpaVendorAdapter()
        adapter.setShowSql(true)
        adapter.setPrepareConnection(true)
        adapter.setDatabase(Database.MYSQL)
        adapter.setDatabasePlatform(dialect)
        adapter.setGenerateDdl(false)
        val properties = HashMap<String, String>()
        properties["hibernate.ddl-auto"] = ddlAuto
        properties["show-sql"] = showSql
        properties["hibernate.naming.implicit-strategy}"] = implicitStrategy
        properties["hibernate.naming.physical-strategy}"] = physicalStrategy
        properties["hibernate.use-new-id-generator-mappings"] = useNewIdGeneratorMappings
        //builder.setCallback(getVendorCallback());
        return EntityManagerFactoryBuilder(
                adapter, properties, persistenceUnitManager)
    }

    @Primary
    @Bean(name = ["jpaEntityManagerFactory"])
    fun jpaEntityManagerFactory(
            @Qualifier("jpaEntityManagerFactoryBuilder") builder: EntityManagerFactoryBuilder,
            @Qualifier("jpaDataSource") jpaDataSource: DataSource?): LocalContainerEntityManagerFactoryBean {
        val factory = builder
                .dataSource(jpaDataSource)
                .packages("com.youngplussoft.sbdemo.jpa.model")
                .persistenceUnit("store")
                .build()
        factory.setPackagesToScan("com.youngplussoft.sbdemo.jpa.model")
        return factory
    }

    @Bean(name = ["jpaTransactionManager"])
    fun jpaTransactionManager(
            @Qualifier("jpaEntityManagerFactory") jpaEntityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        return JpaTransactionManager(jpaEntityManagerFactory)

    }
}