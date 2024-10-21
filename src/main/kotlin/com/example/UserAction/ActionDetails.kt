package com.example.UserAction;

import java.security.Timestamp

data class ActionDetails(
    val userId: String?,
    val sessionId: String?,
    val traceId: String?,
    val globalAddressId: String?,
    val ipAddress: String?,
    val timestamp: Timestamp, // Changed to Google Cloud Timestamp
    val actionType: String,
    val currentStep: String?,
    val selectionData: Map<String, Any>?,
    val errorCode: String?,
    val errorMessage: String
)