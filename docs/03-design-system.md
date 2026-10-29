# PIDROP design system — proposal for approval

One system across both Android roles and admin web; implement once with platform-specific tokens and components. Material 3 is the Android foundation, not a separate visual language per screen.

## Identity and colour

The user supplied a horizontal PIDROP logo image with orange parcel/speed symbol and dark wordmark, with no tagline. The Android app uses a locally bundled crop of that image and a symbol-only crop for small launcher/splash contexts. This supplied direction supersedes the earlier no-motion-trail logo proposal for this prototype; final brand approval, a clean vector master, small-size legibility and monochrome variants still need review. Avoid adding gradients or unrelated illustrations to the wider UI.

| Token | Value | Use |
|---|---|---|
| brand | `#FF6B00` | accents, icons and large action surfaces |
| background/on-brand candidate | `#FFFFFF` | canvas and text on tested surfaces |
| primary text | `#171717` | headings and body |
| secondary text | `#707070` | supplementary copy on white, subject to contrast testing |
| surface | `#F5F5F5` | muted backgrounds |
| border | `#E5E7EB` | dividers and non-critical boundaries |

Orange on white and white on orange may not meet normal-text contrast; do not assume the brand colour is accessible for text or button labels. Use charcoal on orange or a tested accessible variant, and verify all states, disabled treatments and non-text boundaries against WCAG 2.2 AA (4.5:1 normal text, 3:1 large text and essential UI indicators). Do not communicate status by colour alone. Dark mode is not specified; define and test a separate semantic palette before enabling it.

## Type, spacing and geometry

Use system sans/Roboto with Material 3 type roles, subject to device availability: display 32sp/40, headline 24sp/32, title 20sp/28, body 16sp/24, supporting 14sp/20, label 14sp/20; use medium/bold weight for hierarchy. Respect user font scale, multiline content and localization; never force fixed text heights. Spacing tokens: 4, 8, 12, 16, 24, 32, 48dp; default page inset 16dp compact, 24dp wider. Icons: 24dp standard, 20dp compact, 48dp touch target minimum. Shapes: 4dp small, 8dp fields, 12dp cards/sheets; prioritize flat sections and dividers over nested cards. Elevation: flat by default, 1–2dp only for a meaningful overlay; modal scrim per Material.

## Components and states

- Primary button: one prominent action, clear verb, full-width on compact forms where helpful; secondary outlined/text action, destructive confirmation distinct. All actions have destinations and disabled reasons where applicable.
- Text fields: persistent label, useful input type, helper/error text, server and local validation, visible focus, no secret-bearing logs. Numeric OTP/PIN supports paste; never rely on colour alone.
- Loading: progress only while real work is in flight; prevent duplicate submission, preserve context. Inline progress for localized work, screen-level for essential content.
- Empty: explain absence, provide relevant next action; no invented statistics or dummy people. Error: actionable message, retry when safe, preserve input, never display stack traces. Offline: retain local edits where appropriate and label stale data; no false success. Permission denied: explain functionality and provide manual path/settings link where appropriate.
- Use a coherent local/official Material vector icon set; decorative icons have no description, functional icons have localized descriptions. No emoji, fake avatars or runtime icon-fetch APIs.
- Motion: proposed short 150ms, standard 250ms, longer 350ms using Material standard easing; immediate or reduced motion when system accessibility settings require it. Animation must not imply a real operation completed.

## Layout and navigation

Use adaptive window size classes rather than device names: compact single column and bottom-aligned actions where practical; medium/expanded centered content with sensible max width and two panes only where meaningful. Respect system bars, IME, safe areas, rotation, text resizing and back navigation. Avoid blank hero regions, excess cards and meaningless dashboards. Screen titles, primary actions and progress should make the next step clear. Back preserves form state where safe; exit/cancel confirmation is used only when meaningful work would be lost. Customer/rider role choice and separate distribution vs shared app require approval.

## Acceptance

Review brand assets and screen specs before implementation. Test TalkBack order/labels, keyboard, 48dp targets, contrast for each semantic state, large font and narrow/large layouts. No action is inert or suggests a backend operation exists when it does not.
