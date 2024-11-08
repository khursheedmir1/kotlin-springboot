package com.gfiber.useractiontracking.models

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
//    val userAgent: String?,
    val errorCode: String?,
    val errorMessage: String?
)