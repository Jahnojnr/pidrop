# Product requirements and decision register

Status: draft for product, operations, legal and provider review. Source: user brief and PIDROP Product, Business, Technical & Operating Specification V1.0 (13 October 2026), itself a working draft. Where they differ on technology, use the current brief's Kotlin/Compose mobile and Laravel/PostgreSQL/PostGIS backend, not the PDF's proposed Flutter/other backend alternatives.

## Product and scope

PIDROP connects customers sending permitted goods with independent approved riders/drivers using eligible vehicles. The platform coordinates quotes, dispatch, records, support and commissions; it is not a passenger transport product. Customer and rider Android apps and an administrative web dashboard share one authoritative backend. A separate business portal, fleet management and bulk orders are future-scope candidates, not commitments. No stored-value wallet, insurance guarantee, escrow or automatic payout is assumed.

## Requirements

- Customers can create a delivery with pickup and recipient contacts, addresses/landmarks, package information and a suitable vehicle; review an authoritative quote and applicable terms before requesting.
- An eligible approved rider can receive, accept or decline an offer; assignment is exclusive and server-controlled. Pickup confirmation and recipient handover PIN are separate events.
- An authorized participant can see delivery status and rider location only within an approved active-delivery context. Manual address entry remains possible when location access is denied.
- Payment records, delivery status and commission/settlement are independently tracked. Digital payment must be verified by the server. Cash/destination-payment is subject to approved policy; a rider's collection claim is not bank settlement.
- Operations can review riders, deliveries, payment reconciliation, pricing/zones, support, disputes and audit records under least-privilege roles.
- No screen shall imply unverified delivery availability, payment, safety coverage, or third-party capability.

## Decisions and gates (all open unless explicitly noted)

| Topic | Working direction | Approval needed / unresolved |
|---|---|---|
| Geography | One Nigerian launch city | City, polygons, operating hours, rollout and exclusions |
| Vehicle and parcels | Motorcycle, car, van and other approved categories | Actual categories, size/weight/value limits, eligibility, safety checks |
| Restricted goods | Lawful, permitted goods only | Legal-reviewed prohibited/restricted list, consent and escalation |
| Quote | Server-calculated; disclosed before booking | Rates, tax, surcharges, expiry, route changes, fare dispute process |
| Dispatch | Suitable approved rider, exclusive assignment | Matching radius/priority, offer expiry, retry, fraud and fairness rules |
| Payment | Paystack intended; cash/destination-payment considered | Merchant eligibility, channels, who owes/collects, refunds, settlement and failure handling; provider/legal sign-off |
| PIN | Separate from pickup; one-time delivery confirmation | Issuance, delivery/sharing, expiry, retry, override and recipient identity policies |
| Commission | Ledger on eligible delivery fees proposed | Rate/base, accrual trigger, adjustments, taxes, cash liability, settlement cutoff/grace, overdue limits and dispute process |
| Payout | Conditional payout desired in PDF | Provider and counsel confirmation; no escrow or automatic payouts assumed |
| Exceptions | Customer/rider support and audit | Cancellation and fee triggers, no-show, failed delivery, return, loss/damage, refunds and dispute resolution |
| Trust and privacy | Verification and minimal exposure | Evidence types, required documents, retention, consent/lawful basis, location sharing window, data-subject process |
| Legal and safety | Nigeria-first marketplace | Counsel to assess operating classification/licences, contractor status, insurance, tax, payment regulation and data protection |
| Brand | PIDROP, orange/white/charcoal; no tagline in logo | Standalone tagline and final logo approval |
| Business customers | Possible future module | MVP inclusion and roles |

## Non-functional requirements

Accessible touch targets and font scaling; useful low-bandwidth/offline/error states; authoritative server state; auditable privileged and financial changes; privacy by design; idempotent financial and state-changing requests; tested recovery from network failure and concurrent requests. Measurable availability, latency, supported Android versions and retention periods require approval rather than fabricated targets.
