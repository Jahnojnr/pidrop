# PIDROP

PIDROP is a planned Nigerian goods-delivery marketplace. This repository now contains an initial native Android foundation and Stage 0 planning documents; it does **not** contain a backend or admin dashboard.

## Android foundation

The `app/` module uses Kotlin, Jetpack Compose/Material 3, Navigation Compose, Hilt, ViewModel and coroutines. It contains a native system splash (dismissed when the first frame is ready), a welcome/onboarding screen, role selection, separate customer and rider placeholder destinations, and the existing About screen. Get started opens role selection; both roles and the About screen support back navigation. The mark is a provisional vector pending brand approval; the logo has no tagline. No account, booking, payment, maps, location tracking, dispatch or backend integration is implemented. The local repository reports a build capability, not live service availability.

### Setup and verification

This scaffold has **not been compiled or run** in the current environment. It uses a provisional `com.pidrop.app` application ID, min SDK 24, compile/target SDK 35, JDK 17, Gradle wrapper 8.9, and the versions in `gradle/libs.versions.toml`; confirm application ID, supported devices and dependency compatibility before release. No production credentials are included.

In Android Studio or an Android development environment:

1. Install JDK 17, Android SDK Platform 35 and the corresponding Android SDK build tools. Set `JAVA_HOME` and configure the SDK through Android Studio or a local, untracked `local.properties` file (`sdk.dir=...`).
2. Allow access to Google Maven, Maven Central and Gradle distributions. The included wrapper downloads Gradle on first use.
3. Run `./gradlew :app:assembleDebug :app:testDebugUnitTest` (on Windows use `gradlew.bat`). With an emulator or device connected, run `./gradlew :app:connectedDebugAndroidTest` for the navigation UI tests. Install and run the debug APK, then verify splash, navigation/back, screen sizes, large font and accessibility.
4. Review dependency and tooling versions, app signing, application ID, logo and system bar behavior on supported devices before treating this foundation as approved.

No emulator/device or native build was available in the audit sandbox. There is no basis yet to claim the app launches successfully, that tests pass, or that any integration works.

## Project structure

- `app/src/main/java/com/pidrop/app/`: app entry point, `core` (configuration, theme and navigation), `ui`, `domain` repository interface and `data` local implementation.
- `app/src/main/res/`: provisional vector mark, themes and localized strings.
- `app/src/test/`: initial repository unit test (not yet run).
- `app/src/androidTest/`: onboarding and role navigation UI tests (not yet run).
- `gradle/`, root Gradle scripts and wrapper: build configuration.
- `docs/`: product and engineering planning.

## Stage 0 documentation

- [Audit](docs/00-audit.md)
- [Product requirements and decisions](docs/01-product-requirements.md)
- [Screen and interaction inventory](docs/02-screen-inventory.md)
- [Design system](docs/03-design-system.md)
- [Delivery lifecycle](docs/04-delivery-lifecycle.md)
- [API and integration contracts](docs/05-api-integrations.md)
- [Data model](docs/06-data-model.md)
- [Security and privacy](docs/07-security-privacy.md)
- [Stages and acceptance](docs/08-roadmap.md)

These are proposals for review, not evidence that future functionality or integrations exist. The founder's *PIDROP Product, Business, Technical & Operating Specification V1.0* (13 October 2026) is a working draft; legal and payment-provider review remain necessary. Where that draft suggests Flutter or a different backend, the current brief's Kotlin/Compose and Laravel/PostgreSQL/PostGIS technology contract takes precedence.
