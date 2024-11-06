package com.example.useractiontracking.services

import com.example.useractiontracking.entities.UserAction
import com.example.useractiontracking.models.ActionDetails

interface UserActionService {

    suspend fun processAction(actionDetails: ActionDetails)

    suspend fun getAction(actionId: String): UserAction?

}
