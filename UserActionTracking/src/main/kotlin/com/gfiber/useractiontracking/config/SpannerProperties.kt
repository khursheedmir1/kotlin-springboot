package com.gfiber.useractiontracking.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

/**
 * Configuration properties for Google Cloud Spanner.
 * This class is used to bind application properties with the prefix "spanner".
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spanner")
@Validated
data class SpannerProperties(
    /**
     * The Google Cloud project ID.
     */
    @field:NotBlank(message = "Project ID must not be blank")
    val projectId: String = "",

    /**
     * The Spanner instance ID.
     */
    @field:NotBlank(message = "Instance ID must not be blank")
    val instanceId: String = "",

    /**
     * The Spanner database ID.
     */
    @field:NotBlank(message = "Database ID must not be blank")
    val databaseId: String = "",

    /**
     * Feature flag configuration for Spanner.
     */
    val featureFlag: FeatureFlag = FeatureFlag()
) {
    /**
     * Nested class for feature flag configuration.
     */
    data class FeatureFlag(
        /**
         * Flag to enable or disable Spanner integration.
         */
        val enableSpannerIntegration: Boolean = false
    )
}
