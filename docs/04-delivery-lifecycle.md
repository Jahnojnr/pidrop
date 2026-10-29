# Delivery lifecycle — proposed server state machine

Draft only. Validate with operations, legal and payment teams before implementation. Every transition is an atomic server operation with actor authorization, optimistic concurrency/version checks, a unique request/idempotency key where applicable, timestamp, actor, prior/new state and reason in an immutable audit record. Client screens display server truth; FCM or sockets are hints and reconnect fetches the authoritative snapshot. Payment and ledger have independent states.

## Delivery states and transitions

| From → to | Actor / precondition | Audit / failure handling |
|---|---|---|
| draft → quoted | customer; complete eligible locations/package/vehicle; server calculates current quote | quote issued/version/expiry; validation or unavailable zone returns actionable error |
| quoted → requested | customer; accepted unexpired quote, account/consent and approved payment prerequisites | booking created once; expired/changed quote requires review; payment authorization policy TBD |
| requested → matching | system; request valid for dispatch | dispatch started; retry safely |
| matching → assigned | eligible rider accepts unexpired offer; server checks availability and atomically assigns exactly one rider | offer accepted/assignment; race loser gets conflict, no double assignment |
| matching → no_rider_found | system; offer rounds exhausted under approved policy | matching exhausted; customer may retry/requote or cancel |
| no_rider_found → matching | customer/system under approved retry and quote policy | retry recorded; stale quote must be refreshed |
| assigned → en_route_pickup | assigned rider | status recorded; unauthorized/stale action rejected |
| en_route_pickup → at_pickup | assigned rider; pickup arrival requirements TBD | arrival recorded; evidence rules TBD |
| at_pickup → picked_up | assigned rider and authorized pickup contact; approved pickup confirmation/evidence | pickup event recorded independently of delivery PIN; failed confirmation enters exception |
| picked_up → en_route_destination | assigned rider | status recorded |
| en_route_destination → at_destination | assigned rider; arrival rules TBD | arrival recorded |
| at_destination → completed | assigned rider submits valid one-time recipient delivery PIN and any approved proof; server validates/consumes PIN atomically | handover event recorded. Completion does **not** establish payment settlement; incorrect/expired PIN returns controlled error, rate limit/exception flow |
| requested/matching/no_rider_found/assigned/en_route_pickup/at_pickup → cancelled | authorized customer, rider, ops or system; approved cancellation and fee rules | actor/reason/fee decision audited; assignment closed; refund/payment handling separate |
| at_pickup/picked_up/en_route_destination/at_destination → exception | rider/ops; failed handover, damage, safety or other approved reason | case opened, evidence restricted; no implicit completion/refund |
| exception → resumed / return_in_progress / cancelled / completed | authorized ops and explicit approved policy, including evidence and any PIN override | audit decision and reason; override capability/permission is unresolved, never bypass PIN silently |
| return_in_progress → returned | assigned rider/ops; approved return confirmation | custody and any charges recorded; no invented return fee |

`resumed` represents the previously persisted active state, not an uncontrolled transition to any state. Exact names, actor privileges and edge cases require approval. After custody transfer, cancellation vs return and proof requirements are policy decisions; no automatic state change should be released before approval.

## Orthogonal tracks

- Payment: `not_started`, `pending`, `verified`, `failed`, `refund_pending`, `refunded`, `disputed` are proposed records; payment provider status mapping and reconciliation need approval. An authorized amount is not necessarily settled. Cash collection is a separate rider claim, subject to policy and reconciliation.
- Commission: `unassessed`, `accrued`, `adjusted`, `settled`, `disputed` are conceptual, derived from immutable ledger entries; timing and liability are undecided. A handover PIN never settles a payment or commission.
- Delivery requests, payment webhooks and ledger writes must tolerate retries, duplicate callbacks, out-of-order events and worker restarts. Conflicts and partial failure create a visible operational reconciliation case, not a client-side success.
- PINs: use cryptographically random, short-lived one-time secrets, stored as keyed hashes, bound to the delivery, atomically consumed, rate limited and redacted from logs. Exact length, issuance, expiry, notifications and exception process are open. Pickup proof uses a distinct mechanism and record.

## Minimum acceptance

Test all legal and illegal transitions, unauthorized actors, expired quote/offer/PIN, two simultaneous accepts, duplicate PIN submissions, replayed webhooks, offline retry, cancellation races, exceptions and event/audit integrity. Verify completion remains possible or blocked with unsettled payment only according to an explicitly approved policy, without conflating the records.
