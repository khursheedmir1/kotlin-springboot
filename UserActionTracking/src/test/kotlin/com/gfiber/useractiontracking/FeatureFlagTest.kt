package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.config.SpannerProperties
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Test class to verify the behavior of feature flags in SpannerProperties.
 * This class contains unit tests ensuring that feature flags are correctly set and behave as expected.
 */
@DisplayName("Feature Flag Tests")
class FeatureFlagTest {

    /**
     * Test to check that the feature flag enables Spanner integration when set to true.
     */
    @Test
    @DisplayName("Feature flag should enable Spanner integration when true")
    fun `feature flag should enable spanner integration when true`() {
       package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.config.SpannerProperties
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Test class to verify the behavior of feature flags in SpannerProperties.
 * This class contains unit tests ensuring that feature flags are correctly set and behave as expected.
 */
@DisplayName("Feature Flag Tests")
class FeatureFlagTest {

    /**
     * Test to check that the feature flag enables Spanner integration when set to true.
     */
    @Test
    @DisplayName("Feature flag should enable Spanner integration when true")
    fun `feature flag should enable spanner integration when true`() {
        // Given: Initialize the feature flag with enableSpannerIntegration set to true
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)

        // When & Then: Verify that the feature flag reflects the expected value
        assertTrue(featureFlag.enableSpannerIntegration, "Spanner integration should be enabled")
    }

    /**
     * Test to check that the feature flag disables Spanner integration when set to false.
     */
    @Test
    @DisplayName("Feature flag should disable Spanner integration when false")
    fun `feature flag should disable spanner integration when false`() {
        // Given: Initialize the feature flag with enableSpannerIntegration set to false
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = false)

        // When & Then: Verify that the feature flag reflects the expected value
        assertFalse(featureFlag.enableSpannerIntegration, "Spanner integration should be disabled")
    }

    /**
     * Parameterized test to check feature flag behavior with different boolean values.
     * 
     * @param flagValue The boolean value to test the feature flag with.
     */
    @ParameterizedTest(name = "Feature flag should correctly reflect {0}")
    @ValueSource(booleans = [true, false])
    fun `feature flag should correctly reflect given value`(flagValue: Boolean) {
        // Given: Initialize the feature flag with the provided boolean value
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = flagValue)

        // When & Then: Verify that the feature flag reflects the expected value
        assertEquals(flagValue, featureFlag.enableSpannerIntegration, 
            "Spanner integration flag should match the provided value")
    }

    /**
     * Test to verify the default value of the feature flag.
     */
    @Test
    @DisplayName("Feature flag should have correct default value")
    fun `feature flag should have correct default value`() {
        // Given: Initialize the feature flag with default constructor
        val featureFlag = SpannerProperties.FeatureFlag()

        // When & Then: Verify that the default value is false
        assertFalse(featureFlag.enableSpannerIntegration, "Default value should be false")
    }
}
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)

        // When & Then: Verify that the feature flag reflects the expected value
        assertTrue(featureFlag.enableSpannerIntegration, "Spanner integration should be enabled")
    }

    /**
     * Test to check that the feature flag disables Spanner integration when set to false.
     */
    @Test
    @DisplayName("Feature flag should disable Spanner integration when false")
    fun `feature flag should disable spanner integration when false`() {
        // Given: Initialize the feature flag with enableSpannerIntegration set to false
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = false)

        // When & Then: Verify that the feature flag reflects the expected value
        assertFalse(featureFlag.enableSpannerIntegration, "Spanner integration should be disabled")
    }

    /**
     * Parameterized test to check feature flag behavior with different boolean values.
     * 
     * @param flagValue The boolean value to test the feature flag with.
     */
    @ParameterizedTest(name = "Feature flag should correctly reflect {0}")
    @ValueSource(booleans = [true, false])
    fun `feature flag should correctly reflect given value`(flagValue: Boolean) {
        // Given: Initialize the feature flag with the provided boolean value
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = flagValue)

        // When & Then: Verify that the feature flag reflects the expected value
        assertEquals(flagValue, featureFlag.enableSpannerIntegration, 
            "Spanner integration flag should match the provided value")
    }

    /**
     * Test to verify the default value of the feature flag.
     */
    @Test
    @DisplayName("Feature flag should have correct default value")
    fun `feature flag should have correct default value`() {
        // Given: Initialize the feature flag with default constructor
        val featureFlag = SpannerProperties.FeatureFlag()

        // When & Then: Verify that the default value is false
        assertFalse(featureFlag.enableSpannerIntegration, "Default value should be false")
    }
}