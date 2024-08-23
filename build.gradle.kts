plugins {
    id("java")
}

group = "com.seegrid.playingcards"
version = "1.0-SNAPSHOT"

java.sourceCompatibility = JavaVersion.VERSION_22;

repositories {
    mavenCentral()
}

dependencies {
    // Various expressive annotations
    implementation("jakarta.annotation:jakarta.annotation-api:3.0.0")

    // Core Unit Testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Fluent Assertions library
    testImplementation("org.assertj:assertj-core:3.26.3")

    // Mocking support
    testImplementation("org.mockito:mockito-core:5.12.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.12.0")

    // Google Guava Collections
    implementation("com.google.guava:guava:33.2.1-jre")

    // Generated constructors, getters, setters, etc
    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")
    testCompileOnly("org.projectlombok:lombok:1.18.34")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.test {
    useJUnitPlatform()
}