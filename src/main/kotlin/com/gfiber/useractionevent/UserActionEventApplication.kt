package com.gfiber.useractionevent

import com.google.cloud.spring.data.spanner.repository.config.EnableSpannerRepositories
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["com.gfiber.useractiontracking"], exclude = [DataSourceAutoConfiguration::class])
@ComponentScan(basePackages = ["com.gfiber.useractionevent","com.gfiber.useractiontracking"])
@EnableSpannerRepositories("com.gfiber.useractiontracking")
class UserActionEventApplication

fun main(args: Array<String>) {
    runApplication<UserActionEventApplication>(*args)
}