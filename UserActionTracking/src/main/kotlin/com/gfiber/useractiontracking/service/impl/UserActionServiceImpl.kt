package com.gfiber.useractiontracking.service.impl

import com.gfiber.useractiontracking.config.SpannerProperties
import com.gfiber.useractiontracking.entity.UserActions
import com.gfiber.useractiontracking.model.ActionDetails
import com.gfiber.useractiontracking.repository.UserActionRepository
import com.gfiber.useractiontracking.service.UserActionService
import com.gfiber.useractiontracking.util.toSelectionData
import com.google.cloud.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID
import kotlin.coroutines.CoroutineContext

/**
 * Implementation of UserActionService interface.
 * This service handles processing and retrieval of user actions.
 */
@Service
class UserActionServiceImpl(
    @Autowired private val repository: UserActionRepository,
    @Autowired private val properties: SpannerProperties
) : UserActionService {

    private val coroutineContext: CoroutineContext = Dispatchers.IO
    private val logger = LoggerFactory.getLogger(UserActionServiceImpl::class.java)

    /**
     * Processes a user action by saving it to the repository if Spanner integration is enabled.
     *
     * @param actionDetails The details of the action to be processed.
     * @throws Exception if there's an error saving the action.
     */
    override suspend fun processAction(actionDetails: ActionDetails) = withContext(coroutineContext) {
        logger.debug("Processing action with enableSpannerIntegration=${properties.featureFlag.enableSpannerIntegration}")
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
            logger.info("Spanner integration is disabled. Action not saved.")
        }
    }

    /**
     * Retrieves a user action by its ID if Spanner integration is enabled.
     *
     * @param actionId The ID of the action to retrieve.
     * @return The UserAction if found, null otherwise.
     */
    override suspend fun getAction(actionId: String): UserActions? = withContext(coroutineContext) {
        if (properties.featureFlag.enableSpannerIntegration) {
            try {
                repository.findById(actionId).orElse(null)
            } catch (e: Exception) {
                logger.error("Error retrieving action with ID: $actionId", e)
                null
            }
        } else {
            logger.info("Spanner integration is disabled. Unable to retrieve action.")
            null
        }
    }

    /**
     * Builds a UserAction entity from ActionDetails.
     *
     * @param actionDetails The details to build the UserAction from.
     * @return A new UserAction entity.
     */
    private fun buildUserAction(actionDetails: ActionDetails): UserActions {
        return UserActions(
            actionId = generateActionId(),
            userId = actionDetails.userId,
            sessionId = actionDetails.sessionId,
            traceId = actionDetails.traceId,
            globalAddressId = actionDetails.globalAddressId,
            ipAddress = actionDetails.ipAddress,
            timestamp = actionDetails.timestamp,
            actionType = actionDetails.actionType,
            currentStep = actionDetails.currentStep,
            selectionData = actionDetails.selectionData.toSelectionData(),
            errorCode = actionDetails.errorCode,
            errorMessage = actionDetails.errorMessage
        )
    }

    /**
     * Generates a unique action ID.
     *
     * @return A unique string ID.
     */
    private fun generateActionId(): String {
        return UUID.randomUUID().toString()
    }
}