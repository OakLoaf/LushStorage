plugins {
    java
}

dependencies {
    implementation("org.javassist:javassist:3.30.2-GA")

    compileOnly("com.google.auto.service:auto-service-annotations:1.1.1") // Annotation processor registration
    annotationProcessor("com.google.auto.service:auto-service:1.1.1") // Annotation processor registration
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}