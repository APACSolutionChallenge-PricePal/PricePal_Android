plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)

    // Hilt
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.pricepal.test.start"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.pricepal.test.start"
        minSdk = 26
        targetSdk = 35
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.runtime.android)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ✅ Compose BOM (버전 통일 관리)
    implementation(platform(libs.androidx.compose.bom))

    // ✅ Jetpack Compose 기본 UI 구성 요소
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation("androidx.compose.ui:ui-tooling")

    // ✅ 시스템 바 투명 제어 (Splash용)
    implementation(libs.accompanist.systemuicontroller)
    // coil 의존성 추가
    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    // Hilt
    ksp(libs.hilt.compiler)
    implementation(libs.hilt.android)
    // Jetpack Compose 쓰면 아래도
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")

    // 의존성 정의
    implementation(project(":core"))
    implementation(project(":design"))
    implementation(project(":data"))
    implementation(project(":feature:start"))
}