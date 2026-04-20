# Design System Document: Technical High-Contrast Editorial

## 1. Overview & Creative North Star

**Creative North Star: "The Obsidian Terminal"**

This design system is a sophisticated evolution of technical analysis tools. Rather than mimicking the cluttered, utilitarian interfaces of the past, it embraces a "Digital Curator" ethos. It balances the aggressive, high-contrast nature of cybersecurity tools with high-end editorial precision.

The aesthetic is characterized by **intentional density**. We do not fear data; we frame it. The design breaks away from standard "app" templates by using asymmetric layouts, overlapping data visualization layers, and a rigorous adherence to tonal depth. Every element must feel like a precision instrument—sharp, responsive, and authoritative.

---

## 2. Colors: Tonal Depth & Kinetic Accents

The palette is rooted in absolute darkness, using the most profound blacks to provide a canvas for vibrant, kinetic energy.

### Core Palette

- **Background (`#131313`) & Surface (`#131313`):** The foundation. A deep, ink-like void that allows glow effects to pop.
- **Primary Accent (`#A6FF00` - 'Matrix Green'):** Used for active system states, primary CTAs, and critical success data.
- **Secondary Accent (`#00E0FF` - Electric Blue):** Used for informational data streams, secondary highlights, and network latency indicators.
- **Tertiary Accent (`#FF8A00` - Caution Orange):** Reserved strictly for warnings, "high-risk" nodes, and interactive alerts.

### The "No-Line" Rule

Traditional 1px solid borders are strictly prohibited for sectioning content. To define boundaries, designers must use **Background Color Shifts**. For example, a `surface-container-low` section should sit directly on a `surface` background to create a "recessed" or "elevated" feel without the visual noise of a line.

### Surface Hierarchy & Nesting

Treat the UI as a series of physical glass layers. Use the surface-container tiers to create depth:

- **Surface-Container-Lowest (`#0e0e0e`):** For recessed utility areas (e.g., bottom bars).
- **Surface-Container-High (`#2a2a2a`):** For interactive cards or "floating" data modules.
- **The Glass & Gradient Rule:** For hero sections or high-importance data visualizations, use **Glassmorphism**. Apply a semi-transparent surface color with a `20px` backdrop-blur.

### Signature Textures

Main CTAs should not be flat. Apply a subtle linear gradient from `primary` (#A6FF00) to `primary-container` (#467000) at a 45-degree angle to add a sense of physical energy and professional polish.

---

## 3. Typography: Monospaced Precision

The typographic system is a dialogue between human-readable labels and machine-driven data.

- **Display & Headlines (Space Grotesk):** A geometric, clean sans-serif. Used for "Airspace" or "Radar" titles. The wide kerning and sharp angles convey modern authority.
- **Body & Labels (Manrope):** A high-readability sans-serif for descriptions and metadata.
- **Data Layers (System Monospaced):** All real-time telemetry, IP addresses, and signal strengths must use a monospaced font. This ensures that changing numbers do not cause layout shifts and maintains the "terminal" aesthetic.

**Hierarchy Tip:** Use high-contrast scale. A `display-lg` title at 3.5rem should sit near a `label-sm` metadata tag at 0.68rem. This "Editorial Scale" creates visual drama.

---

## 4. Elevation & Depth: Tonal Layering

Shadows in this system are not grey; they are **Ambient Tints**.

- **The Layering Principle:** Avoid "Drop Shadows." To lift a card, place a `surface-container-high` card on a `surface-container-low` background.
- **Ambient Shadows:** When a floating element (like a Tooltip) is required, use a large blur (32px+) with 6% opacity. The shadow color must be a tinted version of `on-surface` (`#e2e2e2`) to simulate light being caught by a glass edge.
- **The Ghost Border Fallback:** If a container requires further definition, use a **Ghost Border**. Apply the `outline-variant` (`#414a34`) at 15% opacity. Never use 100% opaque borders.

---

## 5. Components

### Buttons

- **Primary:** High-vibrancy `primary` background. Sharp corners (`sm` - 0.125rem). Add a subtle `0 0 12px` glow using the `primary` color at 30% opacity when in the 'Active' or 'Scanning' state.

- **Secondary:** Ghost variant. `Ghost Border` with `primary` text.
- **Tertiary:** No border, no background. Purely typographic with a leading icon.

### Cards & Data Modules

- **Forbid Dividers:** Do not separate list items with lines. Use vertical spacing (12px - 16px) or alternate between `surface` and `surface-container-lowest` backgrounds.

- **Glassmorphism:** Use for floating radar overlays or live-feed widgets. (Backdrop-blur: 16px, Opacity: 80%).

### Input Fields

- **State Styling:** Use the `caution orange` (#FF8A00) for error states, but instead of a thick border, use a 1px `Ghost Border` and a soft orange outer glow.

### Additional Components: The "Pulse" Indicator

A unique component for this system: a 4px dot with a concentric, expanding ring animation. Used next to "Live" or "Scanning" labels to denote real-time activity.

---

## 6. Do’s and Don’ts

### Do

- **DO** use asymmetry. Let data visualizations take up 70% of the screen while labels sit in a condensed 30% sidebar.

- **DO** embrace the glow. Use subtle outer glows on active icons to simulate a CRT or high-end OLED display.
- **DO** prioritize monospaced fonts for any numerical value that updates in real-time.

### Don't

- **DON'T** use rounded corners larger than `md` (0.375rem). This system is about "Technical Precision," not "Soft Friendliness."

- **DON'T** use standard grey shadows. They muddy the deep blacks of the background.
- **DON'T** use 1px solid white or grey borders. They break the immersive "Obsidian" feel of the layers.
