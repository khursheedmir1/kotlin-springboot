package com.example.UserAction

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.slf4j.LoggerFactory

/**
 * Main application class for the UserAction Spring Boot application.
 * This class is annotated with @SpringBootApplication, which combines
 * @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 */
@SpringBootApplication
class UserActionApplication

/**
 * Main function to start the Spring Boot application.
 *
 * @param args Command line arguments passed to the application.
 */
fun main(args: Array<String>) {
    val logger = LoggerFactory.getLogger(UserActionApplication::class.java)

    try {
        logger.info("Starting UserActionApplication")
        runApplication<UserActionApplication>(*args)
        logger.info("UserActionApplication started successfully")
    } catch (e: Exception) {
        logger.error("Failed to start UserActionApplication", e)
        // Optionally, you can re-throw the exception if you want the application to exit
        // throw e
    }
}
