package com.example.useractiontracking

import com.example.useractiontracking.configs.SpannerProperties
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FeatureFlagTest {
    @Test
    fun `feature flag should enable spanner integration`() {
        // Given
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)

        // When & Then
        assertTrue(featureFlag.enableSpannerIntegration)
    }
}