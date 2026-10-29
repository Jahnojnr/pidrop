# Staged implementation and acceptance

Each stage is gated by review; preserve any existing project found, and report actual test results and unverified integrations. Stages depend on decisions, contracts and infrastructure; do not silently skip them. Current state: Stage 0 audit/documentation created. The current Stage 1 request adds onboarding and role selection to the native scaffold, including explicit role placeholders. This work remains uncompiled and untested because this environment lacks the Android toolchain and required dependency hosts; build, device and design review gates remain open.

| Stage | Scope | Dependencies and exit criteria |
|---|---|---|
| 0 | Audit and plan | Findings, inventory, architecture, state machine, contracts and decisions documented; product review |
| 1 | Approve logo, design system, navigation and specs; minimum native foundation requested in supplementary brief | Locate any missing project or authorize creation. Provide Android SDK/JDK/Gradle/dependency access; agree IDs/min SDK, compatible stable version catalog, DI and app split. Implement Compose/Material 3 entry/theme/responsive welcome and central navigation, no fake backend; actual build/device/accessibility checks and approval required. Do not treat this as booking/auth. |
| 2 | Launch, onboarding, account entry | Approved content/logo/consent; real navigation/back/restoration, responsive and accessibility checks, offline states; no simulated login |
| 3 | Auth | Approved OTP/session API/provider and credentials; server authorization, storage, expiry/revocation, negative/security tests |
| 4 | Location and package details | Maps config/billing and location/privacy policies; manual entry, denial/offline, restrictions/validation and integration tests |
| 5 | Vehicle/quote/review | Server pricing/zones/eligibility and approved fee rules; expiry, stale quote, display accuracy and backend tests |
| 6 | Payment | Approved provider/settlement model and backend verification/webhook/reconciliation; idempotency, failure and sandbox tests; no client-asserted success |
| 7 | Dispatch and delivery lifecycle | Approved dispatch/eligibility and custody state machine; race, authorization and failure tests |
| 8 | Tracking, pickup and delivery PIN | Location authorization/retention, real-time infrastructure and separate custody policies; physical device, PIN replay/expiry and disconnected tests |
| 9 | Earnings, commission, settlement and history | Approved liability, ledger, timing, settlement, reconciliation/dispute policies; accounting correctness and permissions tests |
| 10 | Administration, support, disputes, security/reporting | RBAC/MFA/audit and policy approvals; operational/security tests, no sensitive data leakage |
| 11 | End-to-end testing and release | Real devices and sizes, accessibility, performance, resilience, privacy/security, provider production verification, legal and operational sign-off; release/rollback plan |

The table preserves stage numbers from the original master brief; the newer Stage 1 request covers splash, onboarding and role selection before authentication. In the original numbering, the supplementary request calls the minimal Android scaffold Stage 1. Stage 1 now contains a provisional scaffold, but its approval and verification gates remain open. No production readiness is inferred from a successful local build.

## Proposed Android architecture after approval

Kotlin + Compose + Material 3, Navigation Compose, ViewModels/coroutines, repository interfaces and implementations, DI via a suitable maintained standard Android solution once versions are compatibility-checked. Gradle Kotlin DSL and version catalog. Suggested root packages `app`, `core` (theme/config/navigation), `ui` (screens/components), `domain` (models/use cases/repository contracts), `data` (repositories and sources). Keep session and network configuration environment-specific; no fake provider implementations presented as real. The new scaffold provisionally uses min SDK 24, application ID `com.pidrop.app`, Hilt and the versions in the catalog; all require confirmation and validation in an Android-capable environment.
