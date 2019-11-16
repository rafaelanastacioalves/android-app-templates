import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
}





kotlin {
    //select iOS target platform depending on the Xcode environment variables
    val iOSTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget =
            if (System.getenv("SDK_NAME")?.startsWith("iphoneos") == true)
                ::iosArm64
            else
                ::iosX64

    iOSTarget("ios") {
        binaries {
            framework {
                baseName = "SharedCode"
            }
        }
    }

    jvm("android")

    sourceSets["commonMain"].dependencies {

        implementation("org.jetbrains.kotlin:kotlin-stdlib-common")

        implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:0.11.0")

        // HTTP
        implementation ("io.ktor:ktor-client-core:1.2.0-rc")
        implementation ("io.ktor:ktor-client-json:1.2.0-rc")
        implementation ("io.ktor:ktor-client-serialization:1.2.0-rc")

        // Coroutines
        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.2.1")

    }

    sourceSets["androidMain"].dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib")
        // HTTP
        implementation ("io.ktor:ktor-client-android:1.2.0-rc")
        implementation ("io.ktor:ktor-client-json-jvm:1.2.0-rc")
        implementation ("io.ktor:ktor-client-serialization-jvm:1.2.0-rc")
        implementation ("io.ktor:ktor-client-okhttp:1.2.0-rc")
        implementation ("com.squareup.okhttp3:logging-interceptor:3.14.1")

    }

    sourceSets["iosMain"].dependencies {
        implementation ("org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:0.11.0")

        // HTTP
        implementation ("io.ktor:ktor-client-ios:1.2.5")
        implementation ("io.ktor:ktor-client-json-native:1.2.0-rc")
        implementation ("io.ktor:ktor-client-serialization-iosx64:1.2.0-rc")


        implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core-native:1.3.2")

    }
}

val packForXcode by tasks.creating(Sync::class) {
    val targetDir = File(buildDir, "xcode-frameworks")

    /// selecting the right configuration for the iOS
    /// framework depending on the environment
    /// variables set by Xcode build
    val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
    val framework = kotlin.targets
            .getByName<KotlinNativeTarget>("ios")
            .binaries.getFramework(mode)
    inputs.property("mode", mode)
    dependsOn(framework.linkTask)

    from({ framework.outputDirectory })
    into(targetDir)

    /// generate a helpful ./gradlew wrapper with embedded Java path
    doLast {
        val gradlew = File(targetDir, "gradlew")
        gradlew.writeText("#!/bin/bash\n"
                + "export 'JAVA_HOME=${System.getProperty("java.home")}'\n"
                + "cd '${rootProject.rootDir}'\n"
                + "./gradlew \$@\n")
        gradlew.setExecutable(true)
    }
}

tasks.getByName("build").dependsOn(packForXcode)