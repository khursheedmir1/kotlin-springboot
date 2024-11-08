plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.gfiber"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")


    implementation(project(":UserActionTracking"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        implementation("com.google.cloud:google-cloud-spanner:6.23.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
        implementation("com.google.cloud:spring-cloud-gcp-starter-data-spanner:5.7.0")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    implementation("org.slf4j:slf4j-api:1.7.36")

        implementation("javax.validation:validation-api:2.0.0.Final")
        implementation("org.springframework.cloud:spring-cloud-gcp-data-spanner:1.2.8.RELEASE")

        testImplementation("org.mockito:mockito-core:5.14.2")
        testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0") // For coroutine test utilities
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    }
}