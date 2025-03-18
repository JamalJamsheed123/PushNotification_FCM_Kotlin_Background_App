buildscript {

    dependencies {
        classpath("com.google.gms:google-services:4.4.2")
     //   classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.22")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.1" apply false
/*
    //hilt plugin
    id("com.google.dagger.hilt.android") version "2.51" apply false*/

}