# Spectrum Design Language Specification

**Version:** 1.1  
**Core Philosophy:** "The Obsidian Terminal"  
**Brand Identity:** Technical, Precise, High-Contrast, Tactical

---

## 1. Design Vision

Spectrum is a design language built for power users, security professionals, and tech enthusiasts. It prioritizes data clarity and high-contrast legibility within a dark, "instrument-panel dark" environment. It draws inspiration from radar systems, tactical terminals, and early cyberpunk aesthetics, but refined for modern, high-resolution displays.

---

## 2. Color Palette

The Spectrum palette is built on extreme contrast to ensure visibility and a focused "hacker tool" vibe, avoiding pure blacks in favor of deep charcoal and bezel tones.

| Layer / Element | Internal Name | Hex Code | Purpose |
| :--- | :--- | :--- | :--- |
| **Surface Base** | Surface | `#07090A` | Base background layer. |
| **Surface Raised** | SurfaceRaised | `#0E1214` | Secondary backgrounds, cards, and elevated containers. |
| **Surface High** | SurfaceHi | `#131719` | Highest elevation surfaces. |
| **Bezel** | Bezel | `#111416` | Structural framing and layout boundaries. |
| **Borders (Frame)** | FrameBorder | `#1C2225` | Distinct borders for layout elements. |
| **Borders (Grid)** | GridLine | `#1A2023` | Subtle grid lines, hair-lines, and internal dividers. |
| **Text Primary** | OnSurface | `#E8EFEC` | Main body text and primary data points. |
| **Text Dimmed** | OnSurfaceDim | `#7C8A86` | Secondary text, kickers, subtitles, and less critical data. |
| **Text Faint** | OnSurfaceFaint | `#3B4543` | Lowest priority text or disabled states. |
| **Accent (Main)** | Accent | `#C8FF4F` | Primary chartreuse action color, oscilloscope traces, and active elements. |
| **Accent Dim** | AccentDim | `#5E7A20` | Subtle accent highlights and borders for unselected/dimmed states. |
| **Secondary Trace**| Accent2 | `#6ED4FF` | Cyan trace color for secondary information. |
| **Success** | Success | `#7BD88F` | Positive states and successful operations. |
| **Warning** | Warning | `#FFCB5E` | Medium-priority alerts, elevated risks, and warnings. |
| **Error / Danger** | Danger | `#FF7A66` | Failures, high-risk security threats, and stop actions. |
| **Severity High** | SeverityHigh | `#FF9B66` | Audit finding severity marker (High). |
| **Severity Low** | SeverityLow | `#8EC5D9` | Audit finding severity marker (Low). |

---

## 3. Typography

Spectrum uses a specific blend of modern sans-serif and monospaced fonts to maintain a technical, engineered look.

* **Primary Font:** `Inter`
* **Monospace Font:** `JetBrains Mono` (Used for data values, labels, kickers, and prominent display text)

### Type Styles

* **Display/Headlines:** `JetBrains Mono`, font-weight: `Medium`, tracking: `-0.02em` to `-0.04em`.
* **Titles:** * Large: `JetBrains Mono`, font-weight: `Medium`, tracking `-0.01em`.
  * Medium: `Inter`, font-weight: `Medium`, tracking `-0.01em`.
* **Body:** `Inter`, font-weight: `Normal`, size: `12sp - 15sp`.
* **Labels / Kickers:** `JetBrains Mono`, font-weight: `Normal`, size: `10sp - 12sp`, wide tracking: `0.18em - 0.20em`. All caps for kickers and section labels.

---

## 4. Visual Elements & UI Patterns

### 4.1. The "Grid System"

Use a subtle background grid using the `GridLine` (`#1A2023`) color to ground the interface in a technical environment. Hairline horizontal dividers (`1.dp` height) are used to separate major sections.

### 4.2. Shape & Roundness

Spectrum relies on slightly softer structural boundaries than a pure sharp terminal, using predefined radii:

* **Extra Small:** `2.dp` (Inner indicators, tooltips)
* **Small:** `4.dp`
* **Medium:** `6.dp` (Cards, filter chips, input containers)
* **Large:** `8.dp`
* **Extra Large / Circular:** `12.dp` or `CircleShape` (Buttons, scan indicators)

### 4.3. Glow & Elevation

* Active states are often depicted by filling the component with the `Accent` color and switching the foreground text to `Surface` to maintain contrast.
* Selected chips and active indicators utilize full-color borders (e.g., `1.dp` border of `Accent`).

### 4.4. Iconography

* **Style:** Outlined, geometric icons.
* **Theme:** Radar, signals, nodes, locks, and telemetry.

---

## 5. Interaction Principles

* **Instant Feedback:** State changes update background and text colors immediately for snappy interaction.
* **The "Scan" Effect:** Whenever data is loading or a scan is active, utilize pulsing elements. For example, a `BlinkingDot` that oscillates alpha transparency (from `0.35f` to `1.0f` over an 800ms linear tween).
* **Data Traces:** Represent continuous data (like RSSI) using oscilloscope-style sine wave traces with dynamic coloring based on signal strength thresholds.

---

## 6. Implementation Notes

When coding for Spectrum:

1. **Dark Mode Only:** There is no light mode or dynamic Android coloring. Spectrum is forced dark using the dedicated `SpectrumColorScheme`.
2. **Contrast is King:** Text must utilize the `OnSurface` hierarchy (`OnSurface`, `OnSurfaceDim`, `OnSurfaceFaint`) against the dark layers.
3. **Data Colors:** Always use the standard thresholds for data values (e.g., RSSI > -60 is `Accent`, > -75 is `Warning`, else `Danger`).
