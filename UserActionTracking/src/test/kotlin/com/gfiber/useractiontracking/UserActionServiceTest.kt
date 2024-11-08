package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.config.SpannerProperties
import com.gfiber.useractiontracking.entity.UserAction
import com.gfiber.useractiontracking.model.ActionDetails
import com.gfiber.useractiontracking.repository.UserActionRepository
import com.gfiber.useractiontracking.service.impl.UserActionServiceImpl
import com.google.cloud.Timestamp
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Mockito.never
import org.mockito.Mockito.any

class UserActionServiceTest {

    private val repository: UserActionRepository = mock(UserActionRepository::class.java)
    private val properties: SpannerProperties = mock(SpannerProperties::class.java)
    private val userActionService = UserActionServiceImpl(repository, properties)

    @Test
    fun `should save user action when feature flag is enabled`(): Unit = runBlocking {

        val timestamp = Timestamp.now()

        // Given
        val actionDetails = ActionDetails(
            userId = "123",
            sessionId = "",
            traceId = "",
            globalAddressId = "",
            ipAddress = "",
            timestamp = timestamp,
            actionType = "",
            currentStep = "",
            selectionData = mapOf(),
            errorCode = "",
            errorMessage = "",
        )

        val userAction = UserAction(
            actionId = "generated-unique-action-id",
            userId = "123",
            sessionId = "",
            traceId = "",
            globalAddressId = "",
            ipAddress = "",
            timestamp = timestamp,
            actionType = "",
            currentStep = "",
            selectionData = mapOf(),
            errorCode = "",
            errorMessage = "",
        )

        val expectedFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)

        `when`(properties.featureFlag).thenReturn(expectedFlag)
        `when`(repository.save(userAction)).thenReturn(userAction)

        // When
        userActionService.processAction(actionDetails)

        // Then
        verify(repository).save(userAction)

    }

    @Test
    fun `should not save user action when feature flag is disabled`(): Unit = runBlocking {
        // Given
        val actionDetails = ActionDetails(
            userId = "123",
            sessionId = "",
            traceId = "",
            globalAddressId = "",
            ipAddress = "",
            timestamp = Timestamp.now(),
            actionType = "",
            currentStep = "",
            selectionData = mapOf(),
            errorCode = "",
            errorMessage = "",
        )

        val expectedFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = false)
        `when`(properties.featureFlag).thenReturn(expectedFlag)

        // When
        userActionService.processAction(actionDetails)

        // Then
        verify(repository, never()).save(any())
    }

}