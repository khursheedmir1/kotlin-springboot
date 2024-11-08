package com.gfiber.useractiontracking.entities

import com.google.cloud.Timestamp
import org.springframework.cloud.gcp.data.spanner.core.mapping.Column
import org.springframework.cloud.gcp.data.spanner.core.mapping.Table
import org.springframework.data.annotation.Id
import javax.validation.constraints.NotBlank

@Table(name = "UserActions")
data class UserAction(

    @Id
    @Column(name = "actionId") @field:NotBlank val actionId: String,

    @Column(name = "userId") val userId: String?,

    @Column(name = "sessionId") val sessionId: String?,

    @Column(name = "traceId") val traceId: String?,

    @Column(name = "globalAddressId") val globalAddressId: String?,

    @Column(name = "ipAddress") val ipAddress: String?,

    @Column(name = "timestamp") val timestamp: Timestamp,

    @Column(name = "actionType") @field:NotBlank val actionType: String,

    @Column(name = "currentStep") val currentStep: String?,

    @Column(name = "selectionData") val selectionData: Map<String, Any>?,

//    @Column(name = "userAgent")
//    val userAgent: String?,

    @Column(name = "errorCode") val errorCode: String?,

    @Column(name = "errorMessage") val errorMessage: String?,
)