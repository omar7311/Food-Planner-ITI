// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript{
    dependencies{
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}



plugins {
    id("com.android.application") version "8.1.1" apply false
    id("com.google.gms.google-services") version "4.3.8" apply false
}


