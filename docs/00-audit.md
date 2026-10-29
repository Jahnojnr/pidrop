# Stage 0 audit

Audit date: 2026-10-29. Baseline: `34379775385f377eb0ab3d146655fd2f331d6850`, branch `arena/b177758b-pidrop`.

This records the Stage 0 baseline before the later native scaffold was added; see the root README for current state.

## What actually existed at the baseline

The baseline tracked exactly one file: `README.md` containing `# pidrop`. No Kotlin, Gradle files or wrapper, Android manifest, resources, assets, navigation, tests, Laravel API, database, admin UI, CI configuration, or integration code exist in this checkout. There is no application entry point or implemented screen to inspect. The premise that a working native Android foundation exists is not supported by this checkout; it may exist elsewhere, but that has not been verified. Existing code was not deleted or regenerated.

## Design and navigation

There are no implemented screens, flows, logo assets, theme, icons or navigation to compare; inconsistency cannot be evaluated empirically. The requested visual and interaction rules are proposed in [design system](03-design-system.md) and [screen inventory](02-screen-inventory.md). In particular, the brief asks both to remove “Pick. Deliver. Done.” from all logo lockups and later identifies it as a tagline: the logo remains tagline-free; any standalone tagline usage needs brand approval.

## Tooling and verification

- `java -version`: `java: command not found`.
- `ANDROID_HOME`, `ANDROID_SDK_ROOT` and `JAVA_HOME` are unset; no `adb`, `sdkmanager`, `gradle` or `javac` was found in the searched standard paths. No Gradle wrapper exists.
- HTTPS probes to `dl.google.com`, `services.gradle.org` and `repo.maven.apache.org` failed in this sandbox; `github.com` was reachable. Android SDK and normal Android/Gradle dependency downloads cannot be assumed available here.
- No build, compilation, emulator/device run, unit test or instrumentation test could be run: there is no project or usable Android toolchain. No tests are claimed to pass.
- This is a sandbox limitation, not a finding about Google AI Studio or the user's own development environment.

## Integrations and contracts

No Maps, Paystack, FCM, SMS/OTP, private document storage, real-time service, backend API or secret/configuration mechanism is implemented or tested. The contracts in this repository are proposed, not live APIs. Version selection, Android application IDs, signing, provider accounts and environment configuration must be set up and verified in a suitable environment; see [contracts](05-api-integrations.md).

## Primary risks

1. Missing claimed codebase: locate it before deciding whether to extend it or create a new Android project; avoid accidental replacement of work stored elsewhere.
2. No Android toolchain/network access: native build and device validation require an appropriately provisioned environment.
3. Launch city, fare, commission, cash liability, settlement, cancellations, goods limits, retention and legal operating model remain open.
4. Paystack merchant eligibility, conditional transfers and settlement must be confirmed with the provider; PIN does not establish payment settlement.
5. Dispatch races, payment replay, location access, OTP/PIN abuse and sensitive document leakage require server-side controls and tests before pilot.
6. The requested Stage 0 approval gate and the additional Stage 1 implementation request appear together; this pass respects the explicit Stage 0 stop and does not claim Stage 1 completion. Approval and an Android-capable build environment are required to proceed.
