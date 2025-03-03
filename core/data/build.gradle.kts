plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.dagger.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "app.example.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 28

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

project.tasks.withType(Test::class.java).configureEach {
    jvmArgs = listOf(
        "-XX:+EnableDynamicAgentLoading",
        "-Dslf4j.provider=org.slf4j.simple.SimpleServiceProvider",
        "-Dnet.bytebuddy.experimental=true"
    )
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    testImplementation (libs.mockk.mockk)
    testImplementation (libs.jetbrains.kotlinx.coroutines.test)
    testImplementation (libs.androidx.datastore.preferences.core)
    testImplementation (libs.slf4j.slf4j.simple)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //dagger-hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation (libs.androidx.hilt.navigation.compose)

    //data store
    implementation (libs.androidx.datastore.preferences)

    //modules
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
}