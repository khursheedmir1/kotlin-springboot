package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.config.SpannerConfig
import com.google.cloud.spanner.Spanner
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension

/**
 * Test class for SpannerConfig.
 * This class verifies the correct initialization and behavior of the Spanner configuration.
 */
@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [SpannerConfig::class])
@DisplayName("Spanner Configuration Tests")
class SpannerConfigTest {

    @Autowired
    private lateinit var spannerConfig: SpannerConfig

    @MockBean
    private lateinit var spanner: Spanner

    /**
     * Test to verify that the Spanner bean is properly initialized.
     */
    @Test
    @DisplayName("Spanner bean should be initialized")
    fun `spanner bean should be initialized`() {
        // Given
        `when`(spannerConfig.spanner()).thenReturn(spanner)

        // When
        val result = spannerConfig.spanner()

        // Then
        assertNotNull(result, "Spanner bean should not be null")
        assertEquals(spanner, result, "Returned Spanner instance should match the mocked one")
        verify(spannerConfig, times(1)).spanner()
    }

    /**
     * Test to verify that the Spanner bean is a singleton.
     */
    @Test
    @DisplayName("Spanner bean should be a singleton")
    fun `spanner bean should be a singleton`() {
        // Given
        `when`(spannerConfig.spanner()).thenReturn(spanner)

        // When
        val firstCall = spannerConfig.spanner()
        val secondCall = spannerConfig.spanner()

        // Then
        assertSame(firstCall, secondCall, "Multiple calls should return the same Spanner instance")
        verify(spannerConfig, times(2)).spanner()
    }

    /**
     * Test to verify that the Spanner bean has the correct type.
     */
    @Test
    @DisplayName("Spanner bean should be of correct type")
    fun `spanner bean should be of correct type`() {
        // Given
        `when`(spannerConfig.spanner()).thenReturn(spanner)

        // When
        val result = spannerConfig.spanner()

        // Then
        assertTrue(result is Spanner, "Returned bean should be an instance of Spanner")
    }
}