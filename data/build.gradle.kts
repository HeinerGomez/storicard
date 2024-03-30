plugins {
    `android-library`
    `kotlin-android`
}

apply(from = "$rootDir/base-module.gradle")

android {
    namespace = "com.avility.data"
}

dependencies {
    implementation(project(Modules.shared))
    implementation(project(Modules.domain))

    implementation(Retrofit.okHttp)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.okHttpLoggingInterceptor)
    implementation(Retrofit.moshiConverter)
    implementation(platform(Google.firebaseBoM))
    implementation(Google.firebaseAnalitycs)
    implementation(Google.firebaseFireStore)
    implementation(Google.firebaseAuth)
    implementation(Google.firebaseStorage)
}