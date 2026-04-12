# todoApp2
# 📱 Android Login & Account List App (MVVM)

## 🚀 Overview

This is a simple Android application built with **Kotlin** following the **MVVM architecture**.
The app simulates a basic login flow and displays a list of accounts (similar to a banking app).

This project was created to practice modern Android development concepts such as:

* MVVM architecture
* State management
* Asynchronous operations using coroutines

---

## 🧩 Features

### 🔐 Login

* User enters email and password
* Input validation handled in ViewModel
* Displays loading, success, and error states

### 📊 Account List

* Displays a list of accounts with balances
* Simulates API calls using coroutine
* Handles loading and error states

---

## 🏗 Architecture

This project follows the **MVVM (Model-View-ViewModel)** pattern:

```text
UI (Activity)
   ↓
ViewModel
   ↓
Repository
   ↓
Data Source (Simulated API)
```

### Responsibilities

* **UI (Activity)**
  Observes state and updates the screen

* **ViewModel**
  Handles UI logic and state management

* **Repository**
  Manages data and simulates API calls

---

## 🔄 Data Flow

1. User interacts with UI (e.g., clicks login)
2. UI calls ViewModel
3. ViewModel triggers Repository
4. Repository simulates API response
5. ViewModel updates state (LiveData)
6. UI observes and updates accordingly

---

## ⚙️ Tech Stack

* Language: Kotlin
* Architecture: MVVM
* Async: Coroutines
* State Management: LiveData
* IDE: Android Studio
* Version Control: Git

---

## 🧠 Key Concepts

### MVVM

Used to separate UI and business logic, making the app more maintainable and testable.

### Coroutines

Used to handle asynchronous operations without blocking the UI thread.

### State Management

Login and data loading are handled using states:

* Loading
* Success
* Error

---

## 🧪 Testing (Basic Concept)

The app is structured to support testing:

* ViewModel logic can be tested independently
* UI reacts to state changes

---

## 📌 Future Improvements

* Add real API integration
* Improve UI/UX design
* Add unit tests for ViewModel
* Implement dependency injection (e.g., Hilt)

---

## 💡 Motivation

This project was built as part of transitioning from web development to mobile development.
It demonstrates understanding of Android architecture and ability to build structured, maintainable applications.

---

## 👤 Author

* Trina Zhang
