plugins {
    base
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    id("org.jetbrains.kotlin.kapt") version "1.4.32"
    id("org.springframework.boot") version "2.4.5"
    id("org.jetbrains.kotlin.plugin.spring") version "1.4.32"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.4.32"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jlleitschuh.gradle.ktlint") version "10.0.0"
}

val javaVersion = JavaVersion.VERSION_1_8.toString()

group = "com.ryulth.springlab"

repositories {
    mavenCentral()
    jcenter()
}

apply(plugin = "kotlin")
apply(plugin = "kotlin-kapt")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")
apply(plugin = "org.jetbrains.kotlin.plugin.spring")
apply(plugin = "org.jetbrains.kotlin.plugin.jpa")
apply(plugin = "io.spring.dependency-management")

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.4.3")
    implementation("io.github.microutils:kotlin-logging:2.0.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework:spring-orm")
    implementation("org.hibernate.reactive:hibernate-reactive-core:1.0.0.CR4")
    implementation("mysql:mysql-connector-java:8.0.22")
    implementation("io.vertx:vertx-mysql-client:4.0.3")
    implementation("org.hibernate:hibernate-jpamodelgen:5.4.31.Final")
    implementation("org.hibernate:hibernate-entitymanager:5.4.31.Final")
    implementation("org.hibernate:hibernate-validator:7.0.1.Final")
    implementation("org.glassfish:jakarta.el:4.0.1")

    testImplementation("io.projectreactor:reactor-test:3.4.6")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
    compileJava {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    compileKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaVersion
        }
    }

    compileTestKotlin {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = javaVersion
        }
    }
}
