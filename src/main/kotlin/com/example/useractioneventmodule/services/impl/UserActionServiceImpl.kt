package com.example.useractioneventmodule.services.impl

import com.example.useractioneventmodule.configs.SpannerProperties
import com.example.useractioneventmodule.entities.UserAction
import com.example.useractioneventmodule.models.ActionDetails
import com.example.useractioneventmodule.repo.UserActionRepository
import com.example.useractioneventmodule.services.UserActionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.coroutines.CoroutineContext

@Service
class UserActionServiceImpl(
) : UserActionService {

    @Autowired
    lateinit var repository: UserActionRepository

    @Autowired
    lateinit var properties: SpannerProperties

    private val coroutineContext: CoroutineContext = Dispatchers.Default
    private val logger = LoggerFactory.getLogger(UserActionServiceImpl::class.java)

    override suspend fun processAction(actionDetails: ActionDetails) = withContext(coroutineContext) {
        if (properties.featureFlag.enableSpannerIntegration) {
            val action = buildUserAction(actionDetails)
            try {
                repository.save(action)
                logger.info("Successfully saved action: actionId=${action.actionId}, userId=${action.userId}, traceId=${action.traceId}")
            } catch (e: Exception) {
                logger.error(
                    "Failed to save action: actionId=${action.actionId}, userId=${action.userId}, traceId=${action.traceId}",
                    e
                )
                throw e
            }
        } else {
            logger.info("Spanner integration is disabled in this environment.")
        }
    }

    override suspend fun getAction(actionId: String): UserAction? = withContext(coroutineContext) {
        if (properties.featureFlag.enableSpannerIntegration) {
            repository.findById(actionId).orElse(null)
        } else {
            logger.info("Spanner integration is disabled in this environment.")
            null
        }
    }

    private fun buildUserAction(actionDetails: ActionDetails): UserAction {
        return UserAction(
            actionId = generateActionId(),
            userId = actionDetails.userId,
            sessionId = actionDetails.sessionId,
            traceId = actionDetails.traceId,
            globalAddressId = actionDetails.globalAddressId,
            ipAddress = actionDetails.ipAddress,
            timestamp = actionDetails.timestamp,
            actionType = actionDetails.actionType,
            currentStep = actionDetails.currentStep,
            selectionData = actionDetails.selectionData,
//            userAgent = actionDetails.userAgent,
            errorCode = actionDetails.errorCode,
            errorMessage = actionDetails.errorMessage
        )
    }

    private fun generateActionId(): String {
        return "generated-unique-action-id"  // Logic to generate a unique actionId
    }

}