# DineTrack local persistence

Restaurant data is stored in the app-private Room/SQLite database `dinetrack.db`.
The existing screens, search/filter behavior, validation, and navigation are retained.
The About screen now describes persistent storage.

## Structure

- Restaurant is the Room entity, with SQLite-generated IDs for new entries.
- RestaurantDao observes rows ordered by ID and provides asynchronous insert/update/delete.
- TagConverters stores tags as JSON, preserving commas, quotes, Unicode, and empty lists.
- DineTrackDatabase is an application-context singleton. Its creation callback inserts the three original sample restaurants once, within database initialization.
- RestaurantRepository normalizes names and ratings and performs DAO operations.
- RestaurantViewModel observes the database and exposes Compose state. Save/delete navigation occurs after a successful write; failures keep the screen available and show an error.

Deleting all restaurants leaves the database empty across restarts. Clearing app storage or uninstalling removes the local database. No destructive migration is configured; future schema versions require an explicit migration. Room schemas are exported under app/schemas.

The existing KSP version requires android.disallowKotlinSourceSets=false with this project's AGP built-in Kotlin setup. Gradle reports this compatibility option as experimental.

## Verification

Run:

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest :app:lintDebug :app:connectedDebugAndroidTest
```

RestaurantPersistenceTest checks first-launch seeding, generated IDs, full-field/tag persistence after reopening, editing, deleting every row, and no reseeding on reopen.
The JVM tests cover required-name validation and name/rating normalization.

For manual verification: add a restaurant with all fields, edit it, force-stop and relaunch the app, search for it, confirm its details, delete it, and relaunch again. Existing share and map/directions prototype dialogs are retained.

## Validated on November 8, 2026

Debug APK assembly, JVM unit tests, lint, and connected Android instrumentation tests completed successfully on the Pixel 7 API 37 emulator. Lint reports 0 errors and 12 warnings. The final database compiler warning cleanup was rebuilt successfully.
