plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.avility.domain"
}

dependencies {
    implementation(project(Modules.shared))
    implementation(platform(Google.firebaseBoM))
    implementation(Google.firebaseAnalitycs)
    implementation(Google.firebaseFireStore)
    implementation(Google.firebaseDataBase)
}