package com.example.useractionevent.repositories

import com.example.useractionevent.entities.UserAction
import com.google.cloud.spring.data.spanner.repository.SpannerRepository
import org.springframework.stereotype.Repository

@Repository
interface UserActionRepository : SpannerRepository<UserAction, String>