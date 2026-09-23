# StudyMate — Feature: Subject Add Screen

> **Branch:** `feature/subject-add-screen`  
> **Status:** Work in Progress (WIP). This branch introduces the layout and flow for creating new course subjects.

---

## Feature Overview

This feature branch focuses on implementing the **Subject Add Screen**, allowing students to add new subjects to their study list.

---

## Current Progress

* **Navigation:** Added the navigation route from `Subject Screen` (via FAB) to `Subject Add Screen` in `NavController`.
* **UI Components:**
    * Created input card for Subject Name.
    * Created input card for Hours and Credits.

---

## Pending Tasks for Branch Merge

- [ ] Add input card for Teachers' Names
- [ ] Add "Save" button with input state handling

---

## Tech Stack

* **Language:** Kotlin
* **UI Framework:** Jetpack Compose
* **Navigation:** Jetpack Navigation (NavController)

---

## Getting Started

1. Clone the repository and switch to this feature branch:
   ```bash
    git clone https://github.com/IlgarM4224/Study-Mate.git
    cd Study-Mate
    git checkout feature/subject-add-screen
   ```
2. Open the project in Android Studio.
3. Sync Gradle dependencies.
4. Run the app on an emulator or physical Android device.