package com.gfiber.useractiontracking.config

import com.google.cloud.spanner.DatabaseId
import com.google.cloud.spanner.Spanner
import com.google.cloud.spanner.SpannerOptions
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean

/**
 * Configuration class for Google Cloud Spanner.
 * This class sets up the necessary beans for interacting with Spanner.
 */
@EnableConfigurationProperties(SpannerProperties::class)
class SpannerConfig(private val properties: SpannerProperties) {

    /**
     * Creates and configures a Spanner service instance.
     *
     * @return Configured Spanner service
     * @throws IllegalArgumentException if projectId is blank
     */
    @Bean
    fun spanner(): Spanner {
        require(properties.projectId.isNotBlank()) { "Project ID must not be blank" }
        return SpannerOptions.newBuilder()
            .setProjectId(properties.projectId)
            .build()
            .service
    }

    /**
     * Creates a DatabaseId instance using the configured properties.
     *
     * @return DatabaseId instance
     * @throws IllegalArgumentException if any of the required properties are blank
     */
    @Bean
    fun databaseId(): DatabaseId {
        require(properties.projectId.isNotBlank()) { "Project ID must not be blank" }
        require(properties.instanceId.isNotBlank()) { "Instance ID must not be blank" }
        require(properties.databaseId.isNotBlank()) { "Database ID must not be blank" }
        
        return DatabaseId.of(
            properties.projectId,
            properties.instanceId,
            properties.databaseId
        )
    }
}