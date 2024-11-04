plugins {
    kotlin("jvm")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
//
//    implementation("com.google.cloud:google-cloud-spanner:6.23.0")
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
//    implementation("com.google.cloud:spring-cloud-gcp-starter-data-spanner:5.7.0")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
////    implementation("org.slf4j:slf4j-api:1.7.36")
//
//    implementation("javax.validation:validation-api:2.0.0.Final")
//    implementation("org.springframework.cloud:spring-cloud-gcp-data-spanner:1.2.8.RELEASE")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}