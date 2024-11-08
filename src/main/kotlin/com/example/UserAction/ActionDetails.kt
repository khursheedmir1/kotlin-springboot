package com.example.UserAction

import com.google.cloud.Timestamp

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
    // Ensure actionType is not blank
    init {
        require(actionType.isNotBlank()) { "actionType must not be blank" }
    }

    // Companion object for creating instances with default values
    companion object {
        /**
         * Creates an instance of ActionDetails with minimal required information.
         *
         * @param actionType Type of action performed
         * @param timestamp Time of the action
         * @param errorMessage Error message (default is empty string)
         * @return ActionDetails instance
         */
        fun createMinimal(
            actionType: String,
            timestamp: Timestamp = Timestamp.now(),
            errorMessage: String = ""
        ): ActionDetails {
            return ActionDetails(
                userId = null,
                sessionId = null,
                traceId = null,
                globalAddressId = null,
                ipAddress = null,
                timestamp = timestamp,
                actionType = actionType,
                currentStep = null,
                selectionData = null,
                errorCode = null,
                errorMessage = errorMessage
            )
        }
    }
}
