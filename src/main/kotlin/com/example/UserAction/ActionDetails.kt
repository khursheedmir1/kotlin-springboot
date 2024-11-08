package com.example.UserAction

import com.google.cloud.Timestamp

/**
 * Represents details of a user action in the system.
 */
data class ActionDetails(
    val userId: String?,
    val sessionId: String?,
    val traceId: String?,
    val globalAddressId: String?,
    val ipAddress: String?,
    val timestamp: Timestamp,
    val actionType: String,
    val currentStep: String?,
    val selectionData: Map<String, Any>?,
    val errorCode: String?,
    val errorMessage: String
) {
    // Companion object for constants and utility functions
    companion object {
        // Define constants for action types
        const val ACTION_TYPE_LOGIN = "LOGIN"
        const val ACTION_TYPE_LOGOUT = "LOGOUT"
        const val ACTION_TYPE_PURCHASE = "PURCHASE"
        // Add more action types as needed

        // Utility function to create an instance with current timestamp
        fun createWithCurrentTimestamp(
            userId: String?,
            sessionId: String?,
            traceId: String?,
            globalAddressId: String?,
            ipAddress: String?,
            actionType: String,
            currentStep: String?,
            selectionData: Map<String, Any>?,
            errorCode: String?,
            errorMessage: String
        ): ActionDetails {
            return ActionDetails(
                userId, sessionId, traceId, globalAddressId, ipAddress,
                Timestamp.now(), actionType, currentStep, selectionData,
                errorCode, errorMessage
            )
        }
    }

    // Validation function
    fun isValid(): Boolean {
        return actionType.isNotBlank() && timestamp != Timestamp.MIN_VALUE
    }

    // ToString override for better logging
    override fun toString(): String {
        return "ActionDetails(userId=$userId, sessionId=$sessionId, " +
               "actionType=$actionType, timestamp=$timestamp)"
    }
}
