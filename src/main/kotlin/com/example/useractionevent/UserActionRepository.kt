package com.example.useractionevent

import com.google.cloud.spring.data.spanner.repository.SpannerRepository
import org.springframework.stereotype.Repository

@Repository
interface UserActionRepository : SpannerRepository<UserAction, String>
{



}