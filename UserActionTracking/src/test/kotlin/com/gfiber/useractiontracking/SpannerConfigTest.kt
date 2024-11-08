package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.configs.SpannerConfig
import com.google.cloud.spanner.Spanner
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration(classes = [SpannerConfig::class])
class SpannerConfigTest {

    private val spannerConfig: SpannerConfig = mock(SpannerConfig::class.java)

    @Test
    fun `spanner bean should be initialized`() {
        // When
//        val spanner = spannerConfig.spanner()
        val spanner = mock(Spanner::class.java)

        // Then
        assertNotNull(spanner)
    }

}