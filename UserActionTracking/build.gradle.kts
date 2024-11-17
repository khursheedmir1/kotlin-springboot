plugins {
    kotlin("jvm")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("maven-publish")
}

group = "com.gfiber"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation("com.google.cloud:google-cloud-spanner:6.81.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("com.google.cloud:spring-cloud-gcp-starter")
    implementation("com.google.cloud:spring-cloud-gcp-starter-data-spanner:5.7.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("javax.validation:validation-api:2.0.0.Final")
    implementation("org.springframework.cloud:spring-cloud-gcp-data-spanner:1.2.8.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testImplementation("org.mockito:mockito-core:5.14.2")
    testImplementation("org.mockito:mockito-junit-jupiter:5.6.0")
    testImplementation("org.mockito.kotlin:mockito-kotlin:5.0.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.0") // For coroutine test utilities
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")

    implementation("com.google.cloud:google-cloud-spanner-jdbc:2.22.0")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("mavenKotlin") {
            from(components["java"])  // This assumes you're building a Java/Kotlin project

            groupId = "com.gfiber"   // Change this to your group ID
            artifactId = "UserActionTracking"   // Change this to your artifact ID
            version = "0.0.1-SNAPSHOT"         // Change this to your version
        }
    }

    repositories {
        mavenLocal()  // Publishes to the local Maven repository
    }
}


tasks.register<Jar>("fatJar") {
    archiveBaseName.set("my-fat-jar")
    archiveVersion.set("1.0.0")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE // Handle duplicates
    // Include compiled classes and runtime dependencies
//    from(sourceSets.main.get().output)
//    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

    from(sourceSets.main.get().output)
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    {
        exclude("META-INF/*.RSA", "META-INF/*.SF", "META-INF/*.DSA")
    }
}

kotlin {
    jvmToolchain(17)
}