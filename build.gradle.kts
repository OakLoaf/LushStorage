plugins {
    java
    `maven-publish`
}

group = "org.lushplugins"
version = "0.1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    compileOnly("org.javassist:javassist:3.30.2-GA")

    compileOnly("com.google.auto.service:auto-service-annotations:1.1.1") // For annotation processor registration
    annotationProcessor("com.google.auto.service:auto-service:1.1.1") // For annotation processor registration
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.generatedSourceOutputDirectory.set(file("$buildDir/generated/sources/annotationProcessor/java/main"))

        doFirst {
            println("AnnotationProcessorPath for $name is ${options.annotationProcessorPath?.asPath}")
        }
    }
}