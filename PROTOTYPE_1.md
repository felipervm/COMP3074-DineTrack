# DineTrack — Prototype 1 (UI)

COMP3074 – Mobile Application Development I · Team P 41  
Felipe da Rocha Vieira Mattos · Student ID: 101581203 · Solo project

## Scope

Seven Compose screens: Splash, Restaurant List, Add/Edit Restaurant, Restaurant Details, Search/Filter, Map & Directions, About.

Working interactions: automatic splash transition, name/tag search, tag chips, add with required-name validation, edit, confirmed deletion, and screen/system back navigation. The Activity-scoped ViewModel keeps changes through navigation and configuration changes. Data resets on process restart. Share and directions display explicit prototype dialogs. No Room, Maps API key, GPS, or new dependencies.

## Run

Open this folder in Android Studio, allow Gradle sync to finish, choose the app run configuration and an API 24+ emulator or device, and press Run. The existing Gradle daemon configuration requires JDK 25.

Build/check from the project terminal:

```powershell
.\gradlew.bat :app:assembleDebug :app:testDebugUnitTest :app:lintDebug
```

## Video checklist

1. Launch: show branded splash and automatic list transition.
2. List: show all three sample restaurants, ratings and tags.
3. Search: try Pai, Pizza, a tag chip, and a query with no matches; open a result.
4. Details: show all restaurant fields and the Share preview.
5. Map: show the styled placeholder and Get Directions preview; return to Details.
6. Add: submit a blank name to show validation, then enter all fields and save; show the new card.
7. Edit: update the new restaurant's name/rating and save.
8. Delete: cancel once, then confirm and show that the card disappears.
9. About: show course, team, full name and student ID.
10. Show toolbar and system back navigation.

## Files

Modified:
- app/src/main/java/ca/gbc/dinetrack/MainActivity.kt
- app/src/main/java/ca/gbc/dinetrack/ui/theme/Color.kt
- app/src/main/java/ca/gbc/dinetrack/ui/theme/Theme.kt
- app/src/main/java/ca/gbc/dinetrack/ui/theme/Type.kt
- app/src/main/res/values/strings.xml

Created under app/src/main/java/ca/gbc/dinetrack/:
- model/Restaurant.kt
- data/MockRestaurantData.kt
- viewmodel/RestaurantViewModel.kt
- navigation/AppScreen.kt
- ui/DineTrackApp.kt
- ui/components/ScreenLayout.kt
- ui/components/RestaurantCard.kt
- ui/components/RatingStars.kt
- ui/components/TagChip.kt
- ui/screens/SplashScreen.kt
- ui/screens/RestaurantListScreen.kt
- ui/screens/AddEditRestaurantScreen.kt
- ui/screens/RestaurantDetailsScreen.kt
- ui/screens/SearchScreen.kt
- ui/screens/MapScreen.kt
- ui/screens/AboutScreen.kt

Also created:
- app/src/test/java/ca/gbc/dinetrack/RestaurantViewModelTest.kt
- PROTOTYPE_1.md
- output/prototype-validation/prototype-build.log
- output/prototype-validation/prototype-build-offline.log
- output/prototype-validation/prototype-build-diagnostic.log

Gradle files, version catalog, SDK levels, package and manifest are preserved.

## Verification status

Gradle assembleDebug, testDebugUnitTest and lintDebug were attempted. This restricted environment could not complete Gradle catalog generation: Java ZipFS reports AccessDeniedException for Gradle library JARs, including a writable workspace copy. The original Java 25 daemon configuration also cannot download its toolchain here. The temporary Java 21 setting was restored byte-for-byte. Kotlin compilation, tests, lint and emulator demonstration remain unverified. Run the command above in Android Studio before recording.
