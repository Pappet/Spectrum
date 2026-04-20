# ViewModel Migration

Lifting screen-local state into `com.isochron.audit.ui.viewmodel.*ViewModel` (AndroidViewModel) so scan results, selections, and in-flight sessions survive tab/nav changes.

## Problem

Composables recompose/discard `remember { mutableStateOf(...) }` on navigation. Users lost scan results, selected items, GATT sessions when switching between Wifi / BT / LAN / etc.

## Pattern

Reference implementation: `WifiScreen` + `WifiViewModel`, mirrored in `BluetoothScreen` + `BluetoothViewModel`.

- Screen takes VM via `viewModel()` default: `fun XScreen(vm: XViewModel = viewModel())`
- Local `val x = vm.x` aliases at top of Composable for readability
- Simple UI state: public `var` (no `private set`) mutated directly from screen — e.g. `vm.filter = "all"`, `vm.selectedAddress = addr`
- State with side-effects: VM helper methods — e.g. `vm.openGatt(addr)` connects GATT + sets address atomically
- Detail/overlay short-circuit: `if (vm.selectedX != null) { DetailView(...); return }`
- Favorites flow: `vm.repository.observeFavorites().collectAsState(...)`
- Resource cleanup: move from screen `DisposableEffect` to VM `onCleared()`

## Done

| Screen | VM | Hoisted state |
|---|---|---|
| `WifiScreen` | `WifiViewModel` | networks, filter, gpsEnabled, selectedNetwork, geoTagCount, uniqueGeoNetworks |
| `BluetoothScreen` | `BluetoothViewModel` | devices, selectedAddress, gattAddress, GATT persistence |
| `LanScreen` | `LanViewModel` | devices, scan progress, networkInfo, portScanResults, portScanningIp, portScanProgress; `NetworkDiscovery.stopScan()` moved to `onCleared()` |

## Pending

- `InventoryScreen`
- `SecurityAuditScreen`
- `MapScreen`
- `BleDetailScreen`
- `OnboardingScreen`

## Open ideas

- Move `WifiScreen` wardriving CSV/KML export block into `WifiViewModel` so screen becomes fully stateless. Currently screen still holds `rememberCoroutineScope()` just for export IO. Trade-off: `Intent.createChooser` + `startActivity` are Activity-scoped — VM would need launcher or event channel. Low priority.
