package com.gfiber.useractiontracking.service

import com.gfiber.useractiontracking.entity.UserAction
import com.gfiber.useractiontracking.model.ActionDetails

interface UserActionService {

    suspend fun processAction(actionDetails: ActionDetails)

    suspend fun getAction(actionId: String): UserAction?

}
