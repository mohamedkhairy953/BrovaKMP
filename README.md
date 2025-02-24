# Brova App

## Overview

Brova is a Kotlin Multiplatform Mobile (KMM) application designed to provide users with a seamless experience in scanning QR codes, saving clothing sizes, and managing their measurements. The app leverages Jetpack Compose for the UI, ensuring a modern and responsive design.

## Features

- **QR Code Scanning**: Users can scan QR codes to retrieve product details and sizes.
- **Measurement Saving**: Users can input and save their clothing measurements for personalized recommendations.
- **History Tracking**: The app maintains a history of scanned items, allowing users to revisit previous scans.
- **User-Friendly Interface**: Built with Jetpack Compose, the app provides a smooth and intuitive user experience.

## Architecture

The app follows a clean architecture pattern, separating concerns into distinct layers:

- **Presentation Layer**: Composed of Composables and ViewModels that handle UI logic and state management.
- **Domain Layer**: Contains business logic and use cases, ensuring that the app remains testable and maintainable.
- **Data Layer**: Responsible for data retrieval and storage, utilizing repositories to abstract data sources.

### Design Patterns

- **MVI (Model-View-Intent)**: The app uses the MVI pattern to manage UI state and user interactions effectively.
- **Repository Pattern**: Data is accessed through repositories, which provide a clean API for data operations.

## Technology Stack

- **Kotlin Multiplatform Mobile (KMM)**: Enables shared code between Android and iOS platforms.
- **Jetpack Compose**: A modern toolkit for building native UI in Android.
- **Ktor**: For making network requests and handling API interactions.
- **Koin**: A lightweight dependency injection framework for Kotlin.

## Project Structure

The project is organized into the following modules:

- **features**: Contains feature-specific code, including UI, ViewModels, and data models.
- **datasource**: Manages data retrieval and storage, including API calls and local storage.
- **viewmodel**: Contains ViewModels that handle UI-related data and business logic.

## Setup Instructions

To set up the project locally, follow these steps:

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/brova-app.git
   cd brova-app
   ```

2. **Open the Project**:
   Open the project in Android Studio or IntelliJ IDEA.

3. **Install Dependencies**:
   Ensure you have the necessary dependencies installed. You can use Gradle to sync the project.

4. **Run the App**:
   - For Android: Connect an Android device or start an emulator, then run the app from Android Studio.
   - For iOS: Open the iOS project in Xcode and run it on a simulator or device.

## Contributing

Contributions are welcome! If you have suggestions or improvements, please create a pull request or open an issue.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Thanks to the Kotlin team for developing KMM.
- Special thanks to the Jetpack Compose community for their contributions to modern Android development.


This is a Kotlin Multiplatform project targeting Android, iOS.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

