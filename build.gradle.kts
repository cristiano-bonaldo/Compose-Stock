// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false

    //id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    //id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false

    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
//    id("org.jetbrains.kotlin.android") version "2.0.0-Beta1" apply false

    //id("com.google.devtools.ksp") version "2.0.0-Beta1-1.0.15" apply false
    id("com.google.devtools.ksp") version "1.9.20-1.0.14" apply false
    id("com.google.dagger.hilt.android") version "2.49" apply false
}