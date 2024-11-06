package com.example.useractiontracking.repo

import com.example.useractiontracking.entities.UserAction
import com.google.cloud.spring.data.spanner.repository.SpannerRepository
import org.springframework.cloud.gcp.data.spanner.repository.config.EnableSpannerRepositories
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Repository

@Repository
@Configuration
@EnableSpannerRepositories
interface UserActionRepository : SpannerRepository<UserAction, String>