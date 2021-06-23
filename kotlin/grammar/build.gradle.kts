plugins {
    kotlin("jvm") version "1.3.72"
}

group = "com.xuzimian.java.learning.kotlin"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

sourceSets.test {
    java.srcDirs("src/main/kotlin")
}

dependencies {
    implementation(kotlin("stdlib"))

    implementation("org.junit.jupiter:junit-jupiter:5.6.0")
}


tasks.withType<Test> {
    useJUnitPlatform()
}