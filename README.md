# DineTrack

DineTrack is a **Personal Restaurant Guide** developed for **COMP3074 – Mobile Application Development I** at George Brown College.

The app helps users save, organize, rate, search, and revisit restaurants they have visited or want to remember.

## Project Information

- **Course:** COMP3074 – Mobile Application Development I
- **Project:** Functional Prototype
- **Team:** P 41
- **Developer:** Felipe da Rocha Vieira Mattos
- **Student ID:** 101581203
- **Platform:** Android
- **Language:** Kotlin
- **UI:** Jetpack Compose
- **Local Storage:** Room / SQLite

## Functional Prototype Scope

This repository contains the functional prototype of DineTrack.

The current version includes:

- Splash screen
- Restaurant list
- Add / Edit Restaurant
- Restaurant Details
- Search / Filter
- Map & Directions prototype
- About screen

The following functionality is implemented:

- Add restaurants
- Edit restaurants
- Delete restaurants with confirmation
- Persistent local storage with Room / SQLite
- Restaurant details
- Search by restaurant name
- Search by tags
- Tag filtering
- 1–5 star rating
- Form validation
- Navigation between screens
- System back navigation
- Reactive UI updates using ViewModel, Flow, and Jetpack Compose

## Data Access Layer

The application now includes a persistent data access layer using Android Room.

The data layer includes:

- `DineTrackDatabase`
- `RestaurantDao`
- `RestaurantRepository`
- `TagConverters`
- Room entity support in the Restaurant model
- Kotlin Coroutines and Flow for asynchronous database operations

Restaurant data remains available after the application is closed and reopened.

The sample restaurants are inserted only when the local database is first created.

## App Purpose

DineTrack gives users a personal place to keep track of restaurants instead of relying on scattered notes, browser bookmarks, or social media posts.

Each restaurant can include:

- Name
- Address
- Phone
- Description
- Tags
- 1–5 star rating

Users can browse their saved restaurants, search by name or tags, open restaurant details, add or edit entries, and manage their personal restaurant collection.

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
│   ├── DineTrackDatabase.kt
│   ├── MockRestaurantData.kt
│   ├── RestaurantDao.kt
│   ├── RestaurantRepository.kt
│   └── TagConverters.kt
├── model/
├── navigation/
├── viewmodel/
└── ui/
    ├── components/
    ├── screens/
    └── theme/
```

## Current Limitations

The Functional Prototype implements the majority of the application logic and the complete local data access layer.

The following integrations remain for the final milestone:

- Live Google Maps integration
- GPS/current-location directions
- Production sharing functionality

The Map & Directions and sharing areas are currently prototype implementations.

## Technologies

- Kotlin
- Android Studio
- Jetpack Compose
- Material 3
- Room
- SQLite
- ViewModel
- Kotlin Coroutines
- Flow
- KSP

## Running the Project

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Allow Gradle sync to complete.
4. Select an Android emulator or physical device.
5. Run the `app` configuration.

Minimum Android version: **API 24+**.

To build from the project terminal on Windows:

```powershell
.\gradlew.bat :app:assembleDebug
```

## Functional Test

A basic functional test can be completed by:

1. Add a new restaurant.
2. Open its Details screen.
3. Edit its information or rating.
4. Search for it by name or tag.
5. Close the application.
6. Reopen the application.
7. Confirm that the restaurant remains saved.
8. Delete the restaurant and confirm the deletion.

## Repository

https://github.com/felipervm/COMP3074-DineTrack

## Author

**Felipe da Rocha Vieira Mattos**  
Student ID: **101581203**  
Team **P 41**
