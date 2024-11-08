package com.gfiber.useractiontracking

import com.gfiber.useractiontracking.entity.UserAction
import com.gfiber.useractiontracking.repository.UserActionRepository
import com.google.cloud.Timestamp
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.springframework.test.context.ContextConfiguration
import java.util.Optional

@ContextConfiguration(classes = [UserActionRepository::class])
class UserActionRepositoryTest {

    private val userActionRepository: UserActionRepository = mock(UserActionRepository::class.java)

    @Test
    fun `should save user action to the database`() {
        // Given
        val userAction = UserAction(
            actionId = "generated-unique-action-id",
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

        // When
        userActionRepository.save(userAction)
//        `when`(userActionRepository.save(userAction)).thenReturn(userAction)

        println("=-=-=>>>  ${userActionRepository.save(userAction)}")

        // Then
        val retrievedAction = userActionRepository.findById("generated-unique-action-id").get()
        assertEquals(userAction, retrievedAction)
    }

    @Test
    fun `should return null for nonexistent action ID`() {
        // Given
        val actionId = "nonexistent-action"

        // When
        val result = userActionRepository.findById(actionId)

        // Then
        assertEquals(Optional.empty<UserAction>(), result)
    }

}