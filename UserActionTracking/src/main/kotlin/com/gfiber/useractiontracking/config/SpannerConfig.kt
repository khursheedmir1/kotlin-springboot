package com.gfiber.useractiontracking.config

import com.google.cloud.spanner.DatabaseId
import com.google.cloud.spanner.Spanner
import com.google.cloud.spanner.SpannerOptions
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean


@EnableConfigurationProperties(SpannerProperties::class)
class SpannerConfig(private var properties: SpannerProperties) {

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