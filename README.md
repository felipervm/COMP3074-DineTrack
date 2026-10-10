# DineTrack

DineTrack is a **Personal Restaurant Guide** developed for **COMP3074 – Mobile Application Development I** at George Brown College.

The app is designed to help users save, organize, rate, search, and revisit restaurants they have visited or want to remember.

## Project Information

- **Course:** COMP3074 – Mobile Application Development I
- **Project:** Prototype 1 – UI
- **Team:** P 41
- **Developer:** Felipe da Rocha Vieira Mattos
- **Student ID:** 101581203
- **Platform:** Android
- **Language:** Kotlin
- **UI:** Jetpack Compose

## Prototype Scope

This repository contains the first UI prototype of DineTrack.

The prototype includes UI for all planned screens:

- Splash
- Restaurant List
- Add / Edit Restaurant
- Restaurant Details
- Search / Filter
- Map & Directions
- About

It also includes working prototype logic for several interactions:

- Automatic transition from Splash to Restaurant List
- Search by restaurant name or tag
- Tag filtering
- Add restaurant with required-name validation
- Edit restaurant
- Delete restaurant with confirmation
- Navigation between all screens
- System back navigation
- In-memory state managed through a ViewModel

The current prototype uses mock/in-memory data. Persistent Room storage, live Google Maps, GPS/location services, and production sharing/directions integrations are planned for later milestones.

## App Purpose

DineTrack gives users a simple personal place to keep track of restaurants instead of relying on scattered notes, browser bookmarks, or social media posts.

Each restaurant can include:

- Name
- Address
- Phone
- Description
- Tags
- 1–5 star rating

Users can browse their saved restaurants, search by name or tags, open restaurant details, add or edit entries, and access prototype map/directions and sharing actions.

## UI Design

The interface follows a consistent Material 3 design with:

- Jetpack Compose components
- Shared theme and typography
- Rounded cards
- Large touch targets
- Consistent spacing
- Simple navigation
- Food/travel-inspired visual styling

## Project Structure

```text
app/src/main/java/ca/gbc/dinetrack/
├── MainActivity.kt
├── data/
├── model/
├── navigation/
├── viewmodel/
└── ui/
    ├── components/
    ├── screens/
    └── theme/
```

## Running the Project

1. Open the repository in Android Studio.
2. Allow Gradle sync to complete.
3. Select an Android emulator or physical device.
4. Run the `app` configuration.

Minimum Android version: **API 24+**.

To build from the project terminal on Windows:

```powershell
.\gradlew.bat :app:assembleDebug
```

## Prototype 1 Requirements Covered

- UI implemented for all planned screens
- Working logic implemented for more than two screens
- Consistent styles/theme used across the app
- Short demonstration video prepared for submission
- Git repository used as project history and submission artifact

## Current Limitations

This is a UI prototype rather than the final implementation. The following are intentionally deferred to later milestones:

- Room/SQLite persistence
- Real Google Maps integration
- GPS/current-location directions
- Final Android sharing implementation
- Full production validation and error handling

## Author

**Felipe da Rocha Vieira Mattos**  
Student ID: **101581203**  
Team **P 41**
