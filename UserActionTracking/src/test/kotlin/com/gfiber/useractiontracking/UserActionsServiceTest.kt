package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.config.SpannerProperties
import com.gfiber.useractiontracking.entity.UserActions
import com.gfiber.useractiontracking.model.ActionDetails
import com.gfiber.useractiontracking.repository.UserActionRepository
import com.gfiber.useractiontracking.service.impl.UserActionServiceImpl
import com.gfiber.useractiontracking.util.toSelectionData
import com.google.cloud.Timestamp
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.Mockito.verify
import org.mockito.Mockito.never
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
@DisplayName("UserActionService Tests")
class UserActionsServiceTest {

    @Mock
    private lateinit var repository: UserActionRepository

    @Mock
    private lateinit var properties: SpannerProperties

    private lateinit var userActionService: UserActionServiceImpl
    private lateinit var testActionDetails: ActionDetails
    private lateinit var testUserActions: UserActions

    @BeforeEach
    fun setup(): Unit {
        userActionService = UserActionServiceImpl(repository, properties)

        val timestamp = Timestamp.now()

        testActionDetails = ActionDetails(
            userId = "test-user-id",
            sessionId = "test-session-id",
            traceId = "test-trace-id",
            globalAddressId = "test-global-address-id",
            ipAddress = "127.0.0.1",
            timestamp = timestamp,
            actionType = "TEST_ACTION",
            currentStep = "TEST_STEP",
            selectionData = mapOf(),
            errorCode = "",
            errorMessage = ""
        )

        testUserActions = UserActions(
            actionId = "test-action-id",
            userId = testActionDetails.userId,
            sessionId = testActionDetails.sessionId,
            traceId = testActionDetails.traceId,
            globalAddressId = testActionDetails.globalAddressId,
            ipAddress = testActionDetails.ipAddress,
            timestamp = testActionDetails.timestamp,
            actionType = testActionDetails.actionType,
            currentStep = testActionDetails.currentStep,
            selectionData = testActionDetails.selectionData.toSelectionData(),
            errorCode = testActionDetails.errorCode,
            errorMessage = testActionDetails.errorMessage
        )
    }

    @Test
    @DisplayName("Should save user action when feature flag is enabled")
    fun `should save user action when feature flag is enabled`(): Unit = runBlocking {
        // Given
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)
        `when`(properties.featureFlag).thenReturn(featureFlag)
        `when`(repository.save(any())).thenReturn(testUserActions)

        // When
        userActionService.processAction(testActionDetails)

        // Then
        verify(repository).save(any())
    }

    @Test
    @DisplayName("Should not save user action when feature flag is disabled")
    fun `should not save user action when feature flag is disabled`(): Unit = runBlocking {
        // Given
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = false)
        `when`(properties.featureFlag).thenReturn(featureFlag)

        // When
        userActionService.processAction(testActionDetails)

        // Then
        verify(repository, never()).save(any())
    }

    @Test
    @DisplayName("Should retrieve user action when it exists")
    fun `should retrieve user action when it exists`(): Unit = runBlocking {
        // Given
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)
        `when`(properties.featureFlag).thenReturn(featureFlag)
        `when`(repository.findById(testUserActions.actionId)).thenReturn(Optional.of(testUserActions))

        // When
        val result = userActionService.getAction(testUserActions.actionId)

        // Then
        assertNotNull(result)
        assertEquals(testUserActions, result)
    }

    @Test
    @DisplayName("Should return null when user action does not exist")
    fun `should return null when user action does not exist`() = runBlocking {
        // Given
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)
        `when`(properties.featureFlag).thenReturn(featureFlag)
        `when`(repository.findById(any())).thenReturn(Optional.empty())

        // When
        val result = userActionService.getAction("non-existent-id")

        // Then
        assertNull(result)
    }
}