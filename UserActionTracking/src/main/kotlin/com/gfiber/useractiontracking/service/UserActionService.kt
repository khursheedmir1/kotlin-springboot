package com.gfiber.useractiontracking.service

import com.gfiber.useractiontracking.entity.UserActions
import com.gfiber.useractiontracking.model.ActionDetails

/**
 * Service interface for handling user actions.
 * This interface defines the contract for processing and retrieving user actions.
 */
interface UserActionService {

    /**
     * Processes a user action.
     *
     * This method is responsible for handling the given action details,
     * which may include persisting the action to a data store or
     * performing any other necessary operations.
     */
    suspend fun processAction(actionDetails: ActionDetails)

    /**
     * Retrieves a user action by its unique identifier.
     *
     * @param actionId The unique identifier of the action to retrieve.
     * @return The UserAction if found, or null if no action exists with the given ID.
     * @throws IllegalArgumentException if the actionId is blank or invalid.
     * @throws RuntimeException if there's an error during retrieval.
     */
    suspend fun getAction(actionId: String): UserActions?

}