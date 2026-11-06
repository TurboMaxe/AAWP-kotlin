plugins {
    `kotlin-dsl`
    id("com.gradleup.shadow") version "8.3.6"
    kotlin("jvm") version "2.2.0"
}

group = "io.turbo"
version = "1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

sourceSets {
    main {
        kotlin.setSrcDirs(files("bukkit/src/main/kotlin"))
    }
}


dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("me.lucko:helper:5.6.14")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.incendo:cloud-paper:2.0.0-beta.10")
    implementation("org.incendo:cloud-kotlin-extensions:2.0.0-beta.10")
    implementation("org.incendo:cloud-kotlin-coroutines:2.0.0-beta.10")
    implementation("org.incendo:cloud-annotations:2.1.0")
    implementation("de.exlll:configlib-yaml:4.8.1")
}


val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
    jvmToolchain(21)
}

tasks {

    named("build"){
        dependsOn("shadowJar")
    }

    withType<ProcessResources> {
        val props = mapOf("version" to version)
        inputs.properties(props)
        filteringCharset = "UTF-8"
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}