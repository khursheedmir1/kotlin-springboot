package com.example.useractionevent.configs

import com.google.cloud.spanner.DatabaseId
import com.google.cloud.spanner.Spanner
import com.google.cloud.spanner.SpannerOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpannerConfig(private val properties: SpannerProperties) {

    @Bean
    fun spanner(): Spanner = SpannerOptions.newBuilder()
        .setProjectId(properties.projectId)
        .build()
        .service

    @Bean
    fun databaseId(): DatabaseId = DatabaseId.of(
        properties.projectId,
        properties.instanceId,
        properties.databaseId
    )
}