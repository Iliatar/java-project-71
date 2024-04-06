plugins {
    id("java")
    application
    distribution
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass = "hexlet.code.App";
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(files("src/main/java/lib/jackson-core-2.17.0.jar"))
    implementation(files("src/main/java/lib/jackson-databind-2.17.0.jar"))
    implementation(files("src/main/java/lib/jackson-annotations-2.15.4.jar"))
}

tasks.test {
    useJUnitPlatform()
}