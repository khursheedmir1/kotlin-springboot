package com.example.useractionevent.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component

@Component
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spanner")
data class SpannerProperties(
    val projectId: String = "",
    val instanceId: String = "",
    val databaseId: String = "",
    val featureFlag: FeatureFlag = FeatureFlag(),
) {
    data class FeatureFlag(
        val enableSpannerIntegration: Boolean = false,
    )
}

