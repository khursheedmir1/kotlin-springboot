package com.gfiber.useractiontracking.entity

import com.google.cloud.Timestamp
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table
import org.springframework.data.annotation.Id
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Represents a user action in the system.
 * This entity is mapped to the "UserActions" table in Google Cloud Spanner.
 */
 
@Table(name = "UserActions")
data class UserAction(
    /**
     * Unique identifier for the action.
     */
    @Id
    @Column(name = "actionId")
    @field:NotBlank(message = "Action ID must not be blank")
    @field:Size(max = 36, message = "Action ID must not exceed 36 characters")
    val actionId: String,

    /**
     * Identifier of the user who performed the action.
     */
    @Column(name = "userId")
    @field:Size(max = 255, message = "User ID must not exceed 255 characters")
    val userId: String?,

    /**
     * Identifier of the session in which the action was performed.
     */
    @Column(name = "sessionId")
    @field:Size(max = 255, message = "Session ID must not exceed 255 characters")
    val sessionId: String?,

    /**
     * Trace ID for distributed tracing.
     */
    @Column(name = "traceId")
    @field:Size(max = 255, message = "Trace ID must not exceed 255 characters")
    val traceId: String?,

    /**
     * Global address identifier associated with the action.
     */
    @Column(name = "globalAddressId")
    @field:Size(max = 255, message = "Global Address ID must not exceed 255 characters")
    val globalAddressId: String?,

    /**
     * IP address from which the action was performed.
     */
    @Column(name = "ipAddress")
    @field:Size(max = 45, message = "IP Address must not exceed 45 characters")
    val ipAddress: String?,

    /**
     * Timestamp when the action was performed.
     */
    @Column(name = "timestamp")
    val timestamp: Timestamp,

    /**
     * Type of action performed.
     */
    @Column(name = "actionType")
    @field:NotBlank(message = "Action type must not be blank")
    @field:Size(max = 100, message = "Action type must not exceed 100 characters")
    val actionType: String,

    /**
     * Current step in a multi-step process, if applicable.
     */
    @Column(name = "currentStep")
    @field:Size(max = 100, message = "Current step must not exceed 100 characters")
    val currentStep: String?,

    /**
     * Additional data associated with the action.
     */
    @Column(name = "selectionData")
    val selectionData: Map<String, Any>?,

    /**
     * Error code, if an error occurred during the action.
     */
    @Column(name = "errorCode")
    @field:Size(max = 50, message = "Error code must not exceed 50 characters")
    val errorCode: String?,

    /**
     * Error message, if an error occurred during the action.
     */
    @Column(name = "errorMessage")
    @field:Size(max = 1000, message = "Error message must not exceed 1000 characters")
    val errorMessage: String?
)