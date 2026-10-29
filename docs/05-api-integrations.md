# API and integration contracts — draft, not implemented

Laravel REST API, PostgreSQL/PostGIS as authoritative services; customer/rider Android and admin web are separate clients. There is no deployed API, API schema or configured provider. Endpoint names below are design proposals, not available endpoints. OpenAPI definitions, versioning, policy and security review are release prerequisites; do not build a production client against this sketch as a finalized contract.

## Protocol and errors

HTTPS `/v1` with JSON UTF-8, ISO-8601 UTC timestamps, opaque UUIDs, E.164 Nigerian phone input and integer minor currency units with ISO currency `NGN` (no floating-point money). Bearer session tokens over TLS, RBAC/ownership authorization server-side. No secrets in app/source. PII redacted in logs. Mutating operations accept `Idempotency-Key` scoped to actor+operation, store request fingerprint/result with expiry to be specified, and reject key reuse with a different payload. State-changing requests carry a version/precondition; stale writes return 409. Responses must include stable request correlation IDs.

Error envelope proposal: `{"error":{"code":"QUOTE_EXPIRED","message":"Please request a new quote.","fields":{},"request_id":"...","retryable":false}}`. Use 400 malformed, 401 unauthenticated, 403 unauthorized, 404 absent or deliberately concealed, 409 state/version or idempotency conflict, 422 validation, 429 rate-limited with `Retry-After`, 503 temporarily unavailable. Localized messages, pagination, error catalog, retry and idempotency retention policies remain to be specified. Never return stack traces, PIN hashes or provider secrets.

## Request/response sketch

These examples define shapes only; fields, paths, capabilities and policies require review. Server ignores/rejects client fare, assignment and payment-success assertions.

| Operation | Proposed request | Proposed response / side effects |
|---|---|---|
| `POST /auth/otp/challenges` | `{phone, purpose, consent_version?}` | `{challenge_id, expires_at, retry_after_seconds}`; neutral response to limit enumeration |
| `POST /auth/otp/verifications` | `{challenge_id, code}` | `{session, account_state}` or controlled invalid/expired/rate-limited error; token delivery/storage design TBD |
| `POST /quotes` | `{pickup:{place_id?,lat?,lng?,address}, destination:{...}, package:{category,weight?,dimensions?}, vehicle_category_id}` | `{quote_id, version, currency, total_minor, breakdown, expires_at, eligibility}` computed by server; route/billing policy TBD |
| `POST /deliveries` | `{quote_id, quote_version, pickup_contact, recipient, instructions, payment_method}` plus idempotency key | `{delivery_id, reference, status, version}`; revalidate quote/eligibility; no client price accepted |
| `GET /deliveries/{id}` | authorized owner, assigned rider or permitted staff | `{id,status,version,participants,quote_snapshot,payment_summary,updated_at}` with role-specific redaction |
| `POST /deliveries/{id}/cancel` | `{version,reason}` | `{status,fee_decision}` under approved policy; no fabricated refund |
| `GET /rider/offers`, `POST /rider/offers/{id}/accept` | accept `{version}` | `{delivery_id,status,version}` or conflict if expired/claimed; atomic exclusive assignment |
| `POST /deliveries/{id}/pickup-confirmations` | `{version,method,evidence_reference?}` | `{status,pickup_confirmed_at}`; policy-approved pickup proof distinct from delivery PIN |
| `POST /deliveries/{id}/handover` | `{version,pin,proof_reference?}` | `{status,completed_at}` on atomic valid PIN consumption; payment status unchanged |
| `POST /deliveries/{id}/location-updates` | `{lat,lng,accuracy_m,observed_at}` from assigned authorized rider | accepted/rejected; validate active assignment, precision, rate, timestamp; access and retention policy TBD |
| `POST /payments` | `{delivery_id,method}` and idempotency key | `{payment_id,status,provider_reference?,authorization_url?}`; amount from server, only approved channels |
| `POST /webhooks/paystack` | provider signed raw body | verify provider signature before parsing/processing, deduplicate, acknowledge safely, then verify transaction server-to-server and reconcile amount/currency/reference/merchant/context before status changes |
| `GET /payments/{id}` | authorized party | `{status,amount_minor,currency}`; status reflects verified server record, not client callback |
| `POST /rider/documents` | authorized upload intent with document type | short-lived private upload authorization or server-mediated upload; scanning/review flow TBD |

Missing contracts include profile and consent, address search/places proxy or SDK access, vehicle eligibility, dispatch offers/declines, status transitions, location subscriptions, OTP/session refresh and revocation, notification registration, support/disputes, verification review, admin RBAC/audit, ledger, refunds, settlements, pagination, privacy requests and real-time message schemas. Define exact input constraints, authorization, preconditions, status codes and acceptance examples in OpenAPI before implementing each stage.

## Integration configuration and verification gates

- **Google Maps Platform**: official Android Maps SDK and approved Places/routes APIs. Configure separate environment-specific keys, billing and enabled APIs. Restrict Android keys to package name and signing certificate SHA-1; restrict server keys by appropriate server-side restrictions. An SDK key embedded in an app is not a secret; protect via restrictions/quotas, never expose unrestricted server credentials. Ask for location only in context; denied permission permits manual selection. Backend authorizes rider location writes/reads only during approved active deliveries. Do not simulate movement. Verify on physical devices, with key restrictions and billed APIs before claiming functionality.
- **Paystack**: provider-approved merchant account and supported channels, environment-specific server secret; publishable client configuration only where needed. Verify webhook signature per current provider documentation against the raw body, verify the transaction server-to-server, compare reference, merchant, amount and currency, and handle replay, out-of-order events and reconciliation idempotently. Confirm actual settlement/transfer arrangement in writing; no assumed escrow, split or automatic payout. Test provider sandbox and documented failure/reconciliation cases; production verification is separate.
- **FCM**: separate Firebase projects/configuration and server-side credentials in secret manager; runtime notification permission where applicable. Tokens are per device, removable on sign-out; notifications are minimal and never contain PINs or sensitive documents. Test real delivery and token refresh; no guaranteed delivery.
- **SMS/OTP**: select approved Nigerian provider after deliverability/cost/privacy review. Server creates, expires, throttles and verifies OTP challenges; configure sender identity and credentials privately. Test expiry, rate limits, retries, enumeration resistance and outages.
- **Document storage**: private encrypted object storage with short-lived narrowly scoped access, upload validation, scanning and role-restricted review. Define region, retention and deletion; no public URLs or permanent client credentials.
- **Real-time**: choose authenticated, authorized channel (e.g. WebSocket service) and configure TLS and secrets. Scope subscriptions to server-authorized active deliveries; revoke on assignment change/completion. Sequence events and resync via REST on reconnect. Polling fallback and provider/scale targets are TBD.

All integration setup, key restrictions, secrets rotation, environment separation, observability, provider agreements and actual tests remain outstanding.
