package com.gfiber.useractiontracking.services

import com.gfiber.useractiontracking.entities.UserAction
import com.gfiber.useractiontracking.models.ActionDetails

interface UserActionService {

    suspend fun processAction(actionDetails: ActionDetails)

    suspend fun getAction(actionId: String): UserAction?

}
