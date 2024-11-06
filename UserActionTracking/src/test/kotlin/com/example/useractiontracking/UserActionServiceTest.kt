package com.example.useractiontracking

import com.example.useractiontracking.configs.SpannerProperties
import com.example.useractiontracking.entities.UserAction
import com.example.useractiontracking.models.ActionDetails
import com.example.useractiontracking.repo.UserActionRepository
import com.example.useractiontracking.services.impl.UserActionServiceImpl
import com.google.cloud.Timestamp
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

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