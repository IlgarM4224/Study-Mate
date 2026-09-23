# StudyMate

StudyMate is an Android application designed for students to manage their academic schedules, course subjects, current grades, and attendance limits.

> **Project Status:** Work in Progress (WIP). The core UI and layout are implemented using mock data.

---

## Tech Stack

* **Language:** Kotlin
* **UI Framework:** Jetpack Compose
* **Navigation:** Jetpack Navigation (NavController)

---

## Current Features

The `main` branch currently includes three completed screens:

### 1. Schedule Screen
* Displays days of the week and the current week type (upper/lower).
* Shows the class schedule for the selected day and week type.
* *Note:* This screen is partially complete and will be finalized after Room database integration.

### 2. Subject Screen
* Displays a list of all enrolled subjects.
* Includes a Floating Action Button (FAB) to navigate to the Subject Add screen (currently under development).

### 3. Subject Detail Screen
* Accessible by tapping on a subject in the Subject Screen.
* Displays the overall grade score.
* Provides a detailed list of individual grades.
* Tracks attendance and remaining absence limits.

---

## Roadmap

- [ ] Complete the Subject Add screen
- [ ] Implement local database storage (Room)
- [ ] Finalize Schedule Screen with Room persistence
- [ ] Add class reminders and notifications

---

## Getting Started

1. Clone the repository:
   ```bash
   git clone [https://github.com/IlgarM4224/Study-Mate](https://github.com/IlgarM4224/Study-Mate)
   
2. Open the project in Android Studio.
3. Sync Gradle dependencies.
4. Run the app on an emulator or physical Android device.