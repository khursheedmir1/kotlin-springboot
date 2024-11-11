package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.entity.UserActions
import com.gfiber.useractiontracking.repository.UserActionRepository
import com.gfiber.useractiontracking.util.toSelectionData
import com.google.cloud.Timestamp
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.Mockito.times
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

/**
 * Unit tests for UserActionRepository.
 * These tests verify the behavior of the UserActionRepository methods.
 */
@ExtendWith(MockitoExtension::class)
@DisplayName("UserActionRepository Tests")
class UserActionsRepositoryTest {

    @Mock
    private lateinit var userActionRepository: UserActionRepository

    private lateinit var testUserActions: UserActions

    @BeforeEach
    fun setup() {
        testUserActions = UserActions(
            actionId = "test-action-id",
            userId = "test-user-id",
            sessionId = "test-session-id",
            traceId = "test-trace-id",
            globalAddressId = "test-global-address-id",
            ipAddress = "127.0.0.1",
            timestamp = Timestamp.now(),
            actionType = "TEST_ACTION",
            currentStep = "TEST_STEP",
            selectionData = mapOf("key" to "value").toSelectionData(),
            errorCode = "",
            errorMessage = ""
        )
    }

    @Test
    @DisplayName("Should save user action to the database")
    fun `should save user action to the database`() {
        // Given
        `when`(userActionRepository.save(testUserActions)).thenReturn(testUserActions)

        // When
        val savedAction = userActionRepository.save(testUserActions)

        // Then
        assertEquals(testUserActions, savedAction, "Saved action should match the original action")
        verify(userActionRepository, times(1)).save(testUserActions)
    }

    @Test
    @DisplayName("Should retrieve user action by ID")
    fun `should retrieve user action by ID`() {
        // Given
        `when`(userActionRepository.findById(testUserActions.actionId)).thenReturn(Optional.of(testUserActions))

        // When
        val retrievedAction = userActionRepository.findById(testUserActions.actionId)

        // Then
        assertTrue(retrievedAction.isPresent, "Retrieved action should be present")
        assertEquals(testUserActions, retrievedAction.get(), "Retrieved action should match the original action")
    }

    @Test
    @DisplayName("Should return empty Optional for nonexistent action ID")
    fun `should return empty Optional for nonexistent action ID`() {
        // Given
        val nonexistentId = "nonexistent-action-id"
        `when`(userActionRepository.findById(nonexistentId)).thenReturn(Optional.empty())

        // When
        val result = userActionRepository.findById(nonexistentId)

        // Then
        assertTrue(result.isEmpty, "Result should be an empty Optional")
    }
}