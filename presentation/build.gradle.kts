plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/compose-module.gradle")

android {
    namespace = "com.avility.presentation"
}

dependencies {
    implementation(project(Modules.shared))
    implementation(project(Modules.domain))

    implementation(Coil.coilCompose)
    implementation(platform(Google.firebaseBoM))
    implementation(Google.firebaseAnalitycs)
    implementation(Google.firebaseFireStore)
    implementation(AndroidX.cameraXCore)
    implementation(AndroidX.cameraXCamera2)
    implementation(AndroidX.cameraXLifeCycle)
    implementation(AndroidX.cameraXCameraView)
    implementation(AndroidX.cameraXCameraExtensions)
    implementation(Google.accompanistPermission)
}