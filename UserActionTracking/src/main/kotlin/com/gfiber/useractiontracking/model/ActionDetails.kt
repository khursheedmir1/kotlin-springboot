package com.gfiber.useractiontracking.model

import com.google.cloud.Timestamp

/**
 * Represents the details of a user action in the system.
 * This data class is used to encapsulate action-related information.
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
    val errorMessage: String?
)