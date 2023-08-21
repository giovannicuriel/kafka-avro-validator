/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/6.8.1/userguide/building_java_projects.html
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.8.10"

    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Schema registry plugin
    id("com.github.imflog.kafka-schema-registry-gradle-plugin") version "1.9.1"

    // Avro plugin
    id("com.github.davidmc24.gradle.plugin.avro") version "1.8.0"

    // Spring stuff
    id ("org.springframework.boot") version "2.5.4"
    id ("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin ("plugin.spring") version "1.5.30"
}
val schemaRegistryUrl="http://localhost:8081"
schemaRegistry {
    url = schemaRegistryUrl
    // url.set(schemaRegistryUrl)

    download {
        subject("br.giovannicuriel.kafka.dog_adoption_event-value", "app/src/main/avro")
    }

    register {
        subject("br.giovannicuriel.kafka.dog_adoption_event-value", "app/src/main/avro/br.giovannicuriel.kafka.dog_adoption_event-value.avsc")
    }
}

avro {
    fieldVisibility.set("PRIVATE")
    stringType.set("CharSequence")
}

repositories {
    mavenCentral()
    maven { url = uri("http://packages.confluent.io/maven/") }
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")

    // Avro dependency
    implementation("org.apache.avro:avro:1.11.1")
    implementation("org.apache.avro:trevni-core:1.11.1")
    implementation("org.apache.avro:trevni-avro:1.11.1")
    implementation ("io.confluent:kafka-avro-serializer:7.3.2")
    implementation ("io.confluent:kafka-schema-registry-client:7.3.2")

    // Spring-related dependencies
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.kafka:spring-kafka")

    // Jackson - this will parse data classes from json
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

application {
    // Define the main class for the application.
    mainClass.set("br.giovannicuriel.kafka.listener.AppKt")
}
