plugins {
    java
    `maven-publish`
}

group = "org.lushplugins"
version = "0.1.0"

dependencies {
    compileOnly(project(":annotations"))
    annotationProcessor(project(":annotations"))
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

allprojects {
    group = rootProject.group
    version = rootProject.version

    repositories {
        mavenLocal()
        mavenCentral()
    }

    tasks {
        withType<JavaCompile> {
            options.encoding = "UTF-8"
        }
    }
}
