# Screen and interaction inventory — review draft

This is a full proposed inventory, not evidence of implemented screens or finalized API contracts. Titles are screen/state identifiers, not promises of separate routes; implementation may combine related states if every listed behavior remains testable. Role navigation and app distribution remain open. Routes and API operations are proposed in [API contracts](05-api-integrations.md); unspecified operations are explicitly **TBD**, not existing endpoints.

## How to read the specifications

Each row specifies purpose/visual hierarchy, entry → action → destination, screen-specific validation and backend dependency. Unless overridden, apply **all** of these shared requirements to every row:

1. **Layout/components:** title and contextual back, primary content, inline helper/error, one primary action, optional secondary text action. Use accessible Material 3 fields, buttons, lists, progress and dialogs from [design system](03-design-system.md), not nested feature cards. Display no fabricated content. Web admin uses the same hierarchy and semantic tokens with adaptive tables/forms.
2. **Actions/navigation:** the named entry and destinations define the main path. Back returns to the entry, preserving valid non-sensitive draft inputs; close returns to the parent. Confirm before discarding unsaved work. Navigation never implies a server operation succeeded. A secondary retry repeats only a safe/idempotent operation; settings opens the relevant system/app settings; unavailable capabilities have no inert button.
3. **States:** loading only while real work occurs; success renders confirmed server data or advances as stated. Empty lists explain why and offer the relevant route where one exists. Offline retains appropriate draft inputs and offers retry; existing server content is marked stale. Failure shows actionable nontechnical text and retry or support; validation errors sit beside fields. Service outage must never appear as an empty result. Apply these states even if the row has no unique exception.
4. **Validation:** required fields and formats are shown in the row or decided in its linked policy. Validate locally for usability and authoritatively on the server. Limits, allowed values and policy thresholds not yet approved must not be invented; absent approval is a release blocker, not a reason to accept everything.
5. **Permissions:** no device permission by default. Maps and location rows request foreground location only on explicit use, with denial and manual search/entry. Background location is separately gated by policy/platform approval. Notification permission is contextual and optional; opening device settings requires user action. Admin operations require server-side RBAC; user data requires object ownership/assignment. Other rows have no device permission unless stated.
6. **Security/privacy:** HTTPS, minimum personal data, role-based redaction and safe logging apply everywhere. Never log OTP/PIN/token or document contents. Session, custody, payment, location and financial restrictions follow [security](07-security-privacy.md); admin screens audit sensitive reads and writes. No client can assert price, assignment or payment success.
7. **Analytics:** no event by default. Only approved, consent/lawful-basis-reviewed aggregate events: `onboarding_completed`, `quote_requested`, `booking_requested`, `offer_accepted`, `handover_completed`, `support_case_created`; include screen/role and non-identifying outcome/correlation ID only. Never send phone, coordinates, documents, OTP/PIN or payment details. Failed event delivery must not block the action.
8. **Acceptance for each row:** test each named entry/action/destination, local and server validation, back/state preservation, loading/success/empty/offline/failure and safe retry, unauthorized access, TalkBack/keyboard, large font, compact/expanded layout, and no false-success/inert control. Also test the specific acceptance item in its row. On a row without data, empty means the correct no-data or not-applicable view rather than a fabricated empty result.

## Foundation and account

| Screen | Purpose / hierarchy and components | Entry → actions → destination | Validation; API / special security | Specific acceptance |
|---|---|---|---|---|
| Launch/splash | Immediate brand and real initialization indicator | App start → resolve first launch/session → onboarding, restoration or welcome/phone | Local startup; no network action assumed | No artificial wait; recover after process death |
| First-launch onboarding | Concise service explanation, page indicator, next/skip | Launch → next/skip → welcome or phone entry | Local completion flag; analytics only if approved | Back/skip and large fonts work; no false service claims |
| Session restoration | Accessible real loading state | Launch → restore → role home or session expiry/phone | Session refresh endpoint TBD; secure storage | Expired/revoked token never opens protected home |
| Offline / service unavailable | Explain cause, retry and relevant manual/draft options | Any affected screen → retry → prior operation; back → prior screen | Connectivity/service health; no invented availability | Offline vs server error distinguishable; drafts retained |
| Welcome | Brand, concise role entry | Launch/onboarding → customer or rider entry → phone/registration | Role/app split TBD; no API | All visible actions lead somewhere |
| Phone entry | Phone label, country context, continue | Welcome/session expiry → submit → OTP | E.164 format; `POST /auth/otp/challenges`; throttle/enumeration controls | Bad number rejected; no account existence disclosure |
| OTP verification | Challenge status, code, resend | Phone/registration → verify → consent/account/profile/home; resend → same screen | Code format/expiry and resend policy TBD; `POST /auth/otp/verifications` | Expired, wrong and rate-limited code handled, no code logged |
| Terms and privacy consent | Versioned links and clear consent action | OTP/account → accept → account creation/profile; decline → exit | Required consent/version policy TBD; consent API TBD | Cannot silently consent; links and decline work |
| Account creation | Name and necessary profile fields | Consent → submit → profile completion or home | Required fields/length TBD; account API TBD | Server validation and duplicate submit handled |
| Profile completion | Missing account information and save | Account creation/settings → save → role home/settings | Required fields TBD; profile API TBD | Do not block on optional fields |
| Session expiry and sign-out | Explain expired session or confirm sign-out | Protected screen/settings → sign in → phone; sign out → welcome | Refresh/revoke API TBD; clear tokens and device push registration | Revoked session cannot access cached private data |

## Customer journey

| Screen | Purpose / hierarchy and components | Entry → actions → destination | Validation; API / special security | Specific acceptance |
|---|---|---|---|---|
| Home | Start delivery, active status and relevant recent activity | Customer home → new → pickup; active → tracking; history/profile/notifications → respective screens | Home data API TBD; empty is honest | No invented jobs, riders or availability |
| Pickup selection | Address, contact and map/search entry | Home → select → destination; map/search → map selection | Required serviceable address/contact; search/eligibility APIs TBD | Manual address possible without GPS |
| Destination selection | Address and recipient details | Pickup → select → instructions; map/search → map selection | Required destination/recipient; service-zone API TBD | Denied permission never blocks manual entry |
| Map selection / address search | Search, map, pin and selected address | Pickup/destination → confirm → calling screen; manual entry → calling screen | Official Maps/Places; foreground permission only on locate; coordinate/address validation TBD | No simulated GPS; handle search/map outage |
| Pickup/delivery instructions | Pickup/recipient contacts, landmarks and notes | Destination → continue → package details | Contact format, field limits TBD; local draft/API TBD | Inputs retained on retry, PII not logged |
| Package details | Category, quantity, size/weight/value where required | Instructions → restrictions; edit → prior fields | Required limits and categories TBD; package eligibility API TBD | Invalid/unsupported parcel cannot proceed silently |
| Package restrictions | Policy summary and acknowledgement | Package details → acknowledge → vehicle selection; back → package details | Legal-approved list/acknowledgement version TBD; policy API TBD | Unapproved policy blocks release, not a fabricated list |
| Vehicle selection | Eligible categories and meaningful selection | Restrictions → select → quote; back → package | Server eligibility and capacity rules TBD; vehicle API TBD | Empty eligible set offers edit/support, not fake availability |
| Server-generated quote | Fare breakdown, expiry and terms | Vehicle → request/refresh → quote; continue → review | `POST /quotes`; fare/expiry server-owned | Expired/changed quote requires new review |
| Booking review | Route, parcel, price, terms and edit links | Quote → edit → relevant prior screen; continue → payment selection | Revalidate quote, terms/consent; `POST /deliveries` only after policy-approved confirmation | Cannot submit client price or duplicate booking |
| Payment-method selection | Approved methods, amounts and terms | Review → select → processing/booking confirmation | Method eligibility/liability policy and API TBD | No method shown without provider/policy support |
| Payment processing | Actual pending operation and status | Payment selection → server confirmation → success/failure | `POST /payments`, `GET /payments/{id}`; verification backend only | Closing/reopening retrieves status without double charge |
| Payment success | Verified payment state, next action | Processing → matching/delivery detail | `GET /payments/{id}`; verified server state | Client callback alone cannot render success |
| Payment failure / retry | Reason safe for customer, retry/change method | Processing → retry → processing; change → payment selection | Provider state/eligibility; idempotent retry | Pending/unknown is not shown as failed or paid |
| Rider matching | Real search status, cancellation | Booking/payment → matching; cancel → cancellation; assigned → rider details | Delivery status/dispatch API TBD | Search exhausted routes to no-rider state, no fake riders |
| No rider found | Honest outcome, retry and cancel | Matching → retry → matching/requote; cancel → cancellation | Retry/quote expiry policy TBD; dispatch API TBD | No false availability or indefinite spinner |
| Assigned rider details | Real assigned identity/vehicle and delivery status | Matching/tracking → track → live tracking; help → support | Authorized delivery API; contact disclosure policy TBD | Another customer's rider/location inaccessible |
| Live tracking | Authorized map, last update age and status | Assigned rider/history → details, support, cancellation when permitted | Delivery/location APIs and channel TBD; foreground permission only if customer locates self | Stale location labeled; no tracking outside authorized active window |
| Pickup confirmation status | Pickup custody timeline | Tracking → status → tracking/details | Pickup event API TBD; no delivery PIN | Pickup confirmation does not complete delivery |
| Delivery PIN / handover | Authorized customer PIN presentation and safe sharing guidance | Tracking → display when authorized; completion → receipt | Issuance/expiry/sharing TBD; PIN API TBD; never logs/notifications | Wrong/expired/replayed PIN rejected; payment remains independent |
| Completion receipt | Confirmed delivery, charges and payment status separately | Completion/history → history detail or support | Delivery/payment APIs TBD | Unsettled payment not presented as paid |
| Delivery history and detail | Honest list, order timeline and receipt | Home → detail → receipt/support | History/detail API TBD; role-based redaction | Empty history and pagination/retry work |
| Cancellation and applicable fees | Reason, approved fee disclosure and confirm | Eligible delivery → confirm → delivery detail; back → delivery | Cancellation API; fee and post-pickup policy TBD | Fee disclosed before action; state conflicts handled |
| Support, complaints and disputes | Issue type, case text/status and help | Delivery/profile → submit → case detail; back → source | Support API TBD; evidence/length policy TBD | Case ID only after server confirms; no private data leakage |
| Notifications | Relevant delivery/account updates | Home → item → authorized delivery/case/settings | Notifications API TBD; contextual OS permission | Denied permission does not block core flow; no PIN in pushes |
| Profile, saved addresses and settings | Account info, addresses, privacy and sign-out | Home → edit/save → profile; addresses → edit/list; sign out → welcome | Profile/address APIs TBD; field limits/retention TBD | Other users' addresses inaccessible; edits validated |

## Rider journey

| Screen | Purpose / hierarchy and components | Entry → actions → destination | Validation; API / special security | Specific acceptance |
|---|---|---|---|---|
| Rider registration | Explain eligibility and required steps | Welcome → register → phone verification | Rider eligibility policy/API TBD | No approval or earnings claims before review |
| Phone verification | Rider challenge and code | Registration → verify → identity submission | Shared OTP contract; policy TBD | Abuse/expiry and resend tests |
| Identity and document submission | Required documents, private upload and progress | Verification → upload → vehicle registration; save → pending | Document rules/API TBD; file type/size checks, private storage | Upload failures recover; documents not publicly accessible |
| Vehicle registration | Vehicle category and evidence | Documents → submit → verification pending | Approved category/vehicle evidence rules and API TBD | Unsupported category rejected on server |
| Verification pending | Current review state and support | Submission → refresh → approved/rejected; support → case | Verification API TBD | Pending never grants availability |
| Verification approved | Approved status and next step | Pending → continue → rider home | Server approval only; API TBD | Role privileges reflect server approval |
| Verification rejected and resubmission | Safe rejection reason and correction path | Pending → edit documents/vehicle → resubmit → pending | Review reason/disclosure and resubmission API TBD | Previous decision audited; no approval by client |
| Rider home | Availability, active job and essential status | Approved → availability/job feed/history/earnings/settings | Rider status API TBD; no fake feed | Offline/verification restriction shown honestly |
| Availability controls | Toggle with eligibility explanation | Rider home → online → eligible job feed; offline → home | Server eligibility/availability API TBD; location policy approval | Toggle shows confirmed server state, failure rolls back |
| Eligible job feed | Authorized offers and expiry | Online → offer → job details; refresh → feed | Offers API; server eligibility; minimize locations | Empty feed honest, expired offer cannot be accepted |
| Job details | Route, package, fee and terms from real offer | Feed → accept → route to pickup; decline → feed | Offer/version/expiry and accept/decline APIs TBD | Simultaneous accept yields only one assignment |
| Accept/decline | Confirm state and resolve conflicts | Job details → accept → pickup route; decline → feed | `POST /rider/offers/{id}/accept`; decline TBD; idempotency | Race loser sees conflict, not success |
| Route to pickup | Assigned route/address and status action | Accepted → arrival → pickup confirmation | Official Maps routes; assignment/location API TBD; contextual location permission | Denial/manual route and stale data handled |
| Pickup confirmation | Independent pickup custody evidence | At pickup → confirm → active delivery; issue → support/exception | Pickup confirmation API; approved method/evidence TBD | No recipient PIN used as pickup proof |
| Active delivery | Custody state and next operational action | Pickup → route destination; issue → support | Authorized delivery API; status transition TBD | Unsupported transitions refused |
| Route to destination | Real route and arrival action | Active delivery → arrive → recipient PIN verification | Maps and authorized location APIs TBD; permission contextual | Location stream ends on unassignment/completion |
| Recipient PIN verification | PIN entry and handover evidence request | Destination → provide required proof first if applicable, then submit PIN → completion; issue → support | `POST /deliveries/{id}/handover`; retry/expiry policy TBD | Replay/invalid/rate limit handled; no payment assumption |
| Delivery proof where required | Required evidence and upload state | Destination → upload/confirm → PIN verification; if proof is optional, continue → PIN verification | Evidence requirements/order TBD; private storage | Cannot claim proof uploaded on network failure |
| Completion and earnings | Server-confirmed completion and provisional/confirmed earnings | Handover/proof → earnings/ledger or home | Delivery and ledger APIs TBD; commission policy TBD | Completion does not imply payment or payout settled |
| Commission ledger and settlement | Line items, liabilities and settlement status | Home/completion → transaction detail or dispute/support | Ledger/settlement APIs and cash policy TBD; finance access limited | Entries reconcile and unsettled balances are not marked paid |
| Delivery history | Completed/failed order list and details | Home → detail → support | History API TBD; authorization | Empty, pagination and stale data handled |
| Support and account settings | Case support, profile, sign-out | Home → support/case; settings → edit/sign out | Support/profile APIs TBD | Revocation and private case ownership tested |

## Administration

| Screen | Purpose / hierarchy and components | Entry → actions → destination | Validation; API / special security | Specific acceptance |
|---|---|---|---|---|
| Secure login | Authenticate staff and MFA | Admin entry → authenticate → overview | Auth/MFA API TBD; session, RBAC, rate limits | Unauthorized user cannot access routes |
| Overview and operational monitoring | Real queue, service health and exceptions | Login → delivery/verification/support detail | Reporting APIs TBD; least privilege | No fabricated metrics; stale/error indicated |
| Customer and rider management | Search, review and account actions | Overview → user detail → approved action | Management APIs and privileges TBD; audit sensitive access | Cross-role authorization and action audit |
| Verification review | Private evidence, decision and reason | Queue → approve/reject → queue/detail | Review APIs TBD; document isolation; reason policy TBD | Decisions audited, rejected rider not eligible |
| Delivery management | Filtered deliveries, status and exception handling | Overview → delivery detail → approved action/support | Lifecycle APIs TBD; version/actor checks | Cannot silently bypass state machine |
| Pricing and service zones | Versioned rules and map boundaries | Admin navigation → edit → review/confirm → list | Pricing/zones APIs TBD; authorized edits, geometry/rate validation | Effective version audited; no invented production rates |
| Vehicle categories and eligibility | Approved categories/capacity policy | Admin navigation → edit → review/confirm → list | Categories API TBD; validation/policy approval | Changes versioned and audited |
| Payment reconciliation | Provider vs internal records, exceptions | Finance → payment detail → investigate/resolve | Reconciliation API TBD; restricted finance permissions | Duplicate/out-of-order events and mismatches visible |
| Commission ledger and settlements | Balances, entries and approved settlement actions | Finance → rider ledger/detail → settlement/dispute | Ledger API TBD; finance RBAC, dual control policy TBD | No client-created balances; corrections auditable |
| Refunds and disputes | Case, evidence and separately verified refund status | Finance/support → review → approved action → case detail | Refund/dispute APIs TBD; provider verification and RBAC | Repeated action cannot duplicate refund |
| Prohibited-goods review | Restricted case and evidence for authorized staff | Operations → review → approve/escalate/reject → case | Goods policy/API TBD; sensitive evidence access | No fabricated prohibition list or unauthorized disclosure |
| Support management | Case queue, assignment, response and resolution | Support → case → reply/escalate/resolve → queue | Support APIs TBD; RBAC and audit | Empty queue truthful; restricted data redacted |
| Permissions, audit logs and reports | Roles, access history and approved reports | Admin navigation → inspect/edit → confirm → audit/detail | RBAC/audit/report APIs TBD; least privilege and step-up | Privilege escalation denied and all changes auditable |
