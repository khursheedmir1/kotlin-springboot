package com.example.useractioneventmodule.repo

import com.example.useractioneventmodule.entities.UserAction
import com.google.cloud.spring.data.spanner.repository.SpannerRepository
import org.springframework.cloud.gcp.data.spanner.repository.config.EnableSpannerRepositories
import org.springframework.stereotype.Repository

@Repository
@EnableSpannerRepositories
interface UserActionRepository : SpannerRepository<UserAction, String>