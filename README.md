# Introduction
Welcome to the repository of MeteorApp, an Android application developed for educational purposes. This app visualise meteorite landing locations based on NASA dataset. This app is written in Kotlin and leverages the power of Jetpack Compose for a modern, declarative UI. It integrates Retrofit for network operations, Google Maps for mapping functionalities, and Geocoder for location services. The data utilized in this app is sourced from NASA's free REST API, providing an insightful look into their publicly available datasets.

## Getting Started

### Prerequisites
- Minimum SDK Version: 31 or higher.
- Android Studio Hedgehog or later.

### Installation
1. Clone the repository to your local machine:
```bash
git clone https://github.com/[YourUsername]/[YourRepoName].git
```
2. Open the project in Android Studio.
3. Edit file `app/src/main/res/values/google_maps_api.xml` and fill you Google Maps API key:
```xml
<string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">PASTE-API-KEY-HERE</string>
```
4. Build the project and run on an Android device or emulator.

## UI examples

Main activity:
![main activity example](/img/meteorapp_example_mainActivity.png)

Landing detail activity:
![landing activity example](/img/meteorapp_example_landingDetailActivity.png)

## Data
NASA API using comprehensive data set from The Meteoritical Society that contains information on all of the known meteorite landings. API is available here: https://data.nasa.gov/Earth-Science/Meteorite-Landings-API/c2vx-j9ed/about_data

## Contributing
As this project is developed for study purposes, contributions, suggestions, and feedback are highly appreciated. Feel free to fork the project and submit your pull requests.

## License
This project is open-sourced under the MIT License.
