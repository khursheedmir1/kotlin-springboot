package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.config.SpannerProperties
import com.gfiber.useractiontracking.entity.UserAction
import com.gfiber.useractiontracking.model.ActionDetails
import com.gfiber.useractiontracking.repository.UserActionRepository
import com.gfiber.useractiontracking.service.impl.UserActionServiceImpl
import com.google.cloud.Timestamp
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
@DisplayName("UserActionService Tests")
class UserActionServiceTest {

    @Mock
    private lateinit var repository: UserActionRepository

    @Mock
    private lateinit var properties: SpannerProperties

    private lateinit var userActionService: UserActionServiceImpl
    private lateinit var testActionDetails: ActionDetails
    private lateinit var testUserAction: UserAction

    @BeforeEach
    fun setup() {
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
            selectionData = mapOf("key" to "value"),
            errorCode = "",
            errorMessage = ""
        )

        testUserAction = UserAction(
            actionId = "test-action-id",
            userId = testActionDetails.userId,
            sessionId = testActionDetails.sessionId,
            traceId = testActionDetails.traceId,
            globalAddressId = testActionDetails.globalAddressId,
            ipAddress = testActionDetails.ipAddress,
            timestamp = testActionDetails.timestamp,
            actionType = testActionDetails.actionType,
            currentStep = testActionDetails.currentStep,
            selectionData = testActionDetails.selectionData,
            errorCode = testActionDetails.errorCode,
            errorMessage = testActionDetails.errorMessage
        )
    }

    @Test
    @DisplayName("Should save user action when feature flag is enabled")
    fun `should save user action when feature flag is enabled`() = runBlocking {
        // Given
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)
        `when`(properties.featureFlag).thenReturn(featureFlag)
        `when`(repository.save(any())).thenReturn(testUserAction)

        // When
        userActionService.processAction(testActionDetails)

        // Then
        verify(repository).save(any())
    }

    @Test
    @DisplayName("Should not save user action when feature flag is disabled")
    fun `should not save user action when feature flag is disabled`() = runBlocking {
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
    fun `should retrieve user action when it exists`() = runBlocking {
        // Given
        val featureFlag = SpannerProperties.FeatureFlag(enableSpannerIntegration = true)
        `when`(properties.featureFlag).thenReturn(featureFlag)
        `when`(repository.findById(testUserAction.actionId)).thenReturn(Optional.of(testUserAction))

        // When
        val result = userActionService.getAction(testUserAction.actionId)

        // Then
        assertNotNull(result)
        assertEquals(testUserAction, result)
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