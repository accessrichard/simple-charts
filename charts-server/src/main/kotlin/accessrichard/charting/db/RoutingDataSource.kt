package accessrichard.charting.db

import accessrichard.charting.config.models.ChartDatSourceConfigProperties
import accessrichard.charting.config.models.DataSourceName
import accessrichard.charting.config.models.DataSourceProps
import com.zaxxer.hikari.HikariDataSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.jdbc.datasource.init.DatabasePopulator
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.stereotype.Component


private val contextHolder: ThreadLocal<String> = ThreadLocal<String>()

fun set(db: String) {
    return contextHolder.set(db)
}

fun get(): String {
    return contextHolder.get()
}

@Component
class RoutingDataSource @Autowired constructor(private val config: ChartDatSourceConfigProperties) : AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any? {
        return contextHolder.get()
    }

    override fun afterPropertiesSet() {
        val target: MutableMap<Any, Any> = hashMapOf()
        val sources = config.sources.filter { !it.driverClassName.isNullOrEmpty() && !it.url.isNullOrEmpty() }
                                    .map {getDataSources(it)}
        for (source in sources){
            target[source.name] = source.dataSource
        }

        setTargetDataSources(target)
        val defaultTarget = sources.first().dataSource
        if ((defaultTarget as HikariDataSource).jdbcUrl == "jdbc:h2:mem:"){
            // val initSchema = ClassPathResource("scripts/schema-h2.sql")
            // val initData = ClassPathResource("scripts/data-h2.sql")
            // val databasePopulator = ResourceDatabasePopulator(initSchema, initData)
            // DatabasePopulatorUtils.execute(databasePopulator, defaultTarget)
        }

        setDefaultTargetDataSource(defaultTarget)
        super.afterPropertiesSet()
    }

    private fun getDataSources(dataSourceProps: DataSourceProps) : DataSourceName {
        val dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName(dataSourceProps.driverClassName)
                .url(dataSourceProps.url)
                .username(dataSourceProps.username)
                .password(dataSourceProps.password)

        try {
            return DataSourceName(dataSourceProps.name, dataSourceBuilder.build())
        } catch (ex: Exception) {
            throw Exception("Data Source Driver is not loaded for ${dataSourceProps.name}.", ex)
        }
    }
}