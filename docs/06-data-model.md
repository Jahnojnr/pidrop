# Data model — conceptual proposal

PostgreSQL/PostGIS; identifiers are opaque UUIDs, timestamps UTC, amounts integer minor units with currency, and sensitive fields minimized/encrypted as appropriate. Constraints, migrations, retention and schema are not implemented. Exact payment and legal semantics await approval.

| Entity | Key fields / relationships | Integrity and access |
|---|---|---|
| User, role, session | user ID, verified phone, profile, roles, device/session metadata | unique verified phone subject to policy; server-managed revocation; role scoped |
| Consent | user, document/version, accepted_at, context | append-only evidence; legal basis and withdrawal policy to review |
| Rider profile, verification, document | user, status, reviewer, document type/private object reference | least-privilege review; no public documents; retain decisions/audit |
| Vehicle, category, eligibility | rider/vehicle, category, approved capacity and status | approval and category rules are server controlled |
| Address/contact | owner, address/place, coordinates, contact role | minimize phone and location access; optional saved addresses separate from immutable delivery snapshot |
| Service zone/pricing policy | polygon geography, effective period/version, category/rates | PostGIS membership; versioned, auditable, effective intervals; no unapproved values |
| Quote | route/location snapshots, package/category, fee breakdown, currency, version, expiry | server-generated immutable offer, expiry and pricing version; no client fare |
| Delivery | customer, quote, pickup/recipient snapshots, assigned rider, status/version | server transition checks, exclusive assignment, optimistic concurrency |
| Offer/assignment | delivery, rider, offer expiry/status, assignment timestamps | transaction/unique active assignment; acceptance race-safe |
| Delivery event/custody | delivery, actor, from/to, reason, pickup/handover evidence reference | append-only, distinct pickup and delivery events; redact sensitive contents |
| PIN challenge | delivery, keyed hash, issued/expiry/consumed time, attempts | one-time atomic consume; never store/log plaintext; retry/expiry policy TBD |
| Location update | delivery, rider, timestamp, geography point, accuracy | authorized active delivery only; retention and precision policy TBD; spatial/time indexes |
| Payment/attempt/provider event | delivery, amount, currency, provider reference, status and deduplicated event ID | unique provider reference/event, verification and reconciliation; separate from handover |
| Ledger account/entry/settlement | actor, delivery, debit/credit or signed amount, currency, reason/reference | append-only balanced entries where applicable; idempotent posting, corrections via compensating entries, no assumed payout |
| Support case/dispute/refund | delivery/payment, type/status, assignee, outcome and evidence | role-limited, audit every action; refund state independently verified |
| Notification/device | user/device token, delivery preference, event | token revocation and minimal payload |
| Audit log | actor, action, target, timestamp, correlation ID, redacted metadata | append-only/tamper-evident controls; privileged access and retention policy TBD |

Use transactions and uniqueness constraints for assignment, event deduplication and financial posting; outbox pattern for reliable side effects after commit. Define deletion/anonymization and legal holds with counsel before production. Never use live location as proof of payment or a delivery PIN as proof of settlement.
