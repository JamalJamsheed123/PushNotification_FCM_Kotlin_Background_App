import org.jetbrains.kotlin.gradle.internal.Kapt3GradleSubplugin.Companion.isIncrementalKapt

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.pushnotificationfcm"
    compileSdk = 34
    buildToolsVersion = "33.0.1"

    defaultConfig {
        applicationId = "com.example.pushnotificationfcm"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

   /* buildFeatures {
        this.viewBinding = true   //both allow are view bind classes/activity findviewbyid ki jaga yeh
        this.dataBinding = true   // appropiate approach ha isse performance aur biolerplate code reduce
                                  // hota ha.

    }*/

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
    implementation("com.google.firebase:firebase-messaging:24.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

    //Firebase FCM

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:28.3.1"))

    // Add the dependencies for the Firebase Cloud Messaging and Analytics libraries
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-messaging")
    implementation("com.google.firebase:firebase-analytics-ktx")


}


