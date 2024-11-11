package com.gfiber.useractiontracking.repository

import com.gfiber.useractiontracking.entity.UserActions
import com.google.cloud.spring.data.spanner.repository.SpannerRepository
import org.springframework.stereotype.Repository

/**
 * Repository interface for UserAction entities.
 *
 * This interface extends SpannerRepository to provide CRUD operations
 * for UserAction entities in Google Cloud Spanner.
 */


@Repository
interface UserActionRepository : SpannerRepository<UserActions, String> {

}

