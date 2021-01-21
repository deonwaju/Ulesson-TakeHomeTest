# Ulesson-TakeHomeTest


# ULesson test

First we take you through the UI.

# Welcome Screen

The user is met with a display with buttons labeled after subjects, where they can choose the subject video they want to study, and just below the buttons,
there is also the recently watched video segment. This segment is empty at first till a user chooses to watch a video, hereafter the recently played segment 
is filled with recently watched videos. (watch this space for the screens)

![WhatsApp Image 2021-01-20 at 20 10 42](https://user-images.githubusercontent.com/54560535/105226031-ba446f80-5b5f-11eb-9f31-2fa4656bd3b6.jpeg)

# Subject Screens
On clicking the buttons, the user is taking to the screens where they can select a subjec topic video to watch.


![WhatsApp Image 2021-01-20 at 20 10 41 (3)](https://user-images.githubusercontent.com/54560535/105226121-dba55b80-5b5f-11eb-9d76-f40b9f75fa29.jpeg)

# Play video screen
then after the user chooses a topic, they are brought to the video playback section where they can study the chosen topic.

![WhatsApp Image 2021-01-20 at 20 10 41](https://user-images.githubusercontent.com/54560535/105226142-e2cc6980-5b5f-11eb-8636-6707f82f7c65.jpeg)


On return to welcome screen, there are recently played videos in the recently played area, first a set at a limit of two is.

![WhatsApp Image 2021-01-20 at 20 10 39](https://user-images.githubusercontent.com/54560535/105226396-3939a800-5b60-11eb-9552-b41b954f8ef0.jpeg)

Then on the user clicking the expand button, the user can see more recently played videos


![WhatsApp Image 2021-01-20 at 20 10 40 (1)](https://user-images.githubusercontent.com/54560535/105226544-67b78300-5b60-11eb-935e-99ce506524ae.jpeg)

# DataBase
In this project, there are two tables present in the Room database, the first table is used to store the subjects as it is fetched from the API endpoint,
the second table contains subjects that where recently viewed by the user. 
  Furthermore, Because the home page has a collapseable button that first sets a limit of two recently viewed subject topics, there is a limit of two 
  set to the recently viewed subject topics, until the user chooses to click the expandable button.  

* Room: The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* Navigation component: a library that manages and eases fragment transactions
* Hilt: for dependency injection
* LiveData: An observable data holder class consumed by the layout to display ui data
* ViewModel: a class that housed UI-related data
* Retrofit: for making network request
* Coroutine: recommended way to make execute asynchronous code, main safe!, and eliminates call back hell code
* Timber: used for logging
* Glide: a library for loading images
* Exoplayer : library used for playing videos



Access learning resources.
Summary
The app pulls subject/media content from uLesson API. It is built according to the Model-View-ViewModel(MVVM) architecture. Implementing the Google Room database,
retrofit for API calls.

# Architecture
The application follows clean architecture because of the benefits it brings to software which includes scalability, maintainability and testability. It enforces separation of concerns and dependency inversion, where higher and lower level layers all depend on abstractions. In the project, the layers are separated into different gradle modules namely:


These modules are Kotlin modules except the cache module. The reason being that the low level layers need to be independent of the Android framework. One of the key points of clean architecture is that low level layers should be platform agnostic. As a result, the domain, data and presentation layers can be plugged into a kotlin multiplatform project for example, and it will run just fine because we don't depend on the android framework. The cache and remote layers are implementation details that can be provided in any form (Firebase, GraphQl server, REST, ROOM, SQLDelight, etc) as long as it conforms to the business rules / contracts defined in the data layer which in turn also conforms to contracts defined in domain. The project has one feature module dashboard that holds the UI code and presents data to the users. The main app module does nothing more than just tying all the layers of our app together.

For dependency injection and asynchronous programming, the project uses Dagger Hilt and Coroutines with Flow. Dagger Hilt is a fine abstraction over the vanilla dagger boilerplate, and is easy to setup. Coroutines and Flow brings kotlin's expressibility and conciseness to asynchronous programming, along with a fine suite of operators that make it a robust solution.

Features
Clean Architecture with MVVM (Uni-directional data flow)
Kotlin Coroutines with Flow
Dagger Hilt
Kotlin Gradle DSL
Prerequisite
To build this project, you require:

                Android Studio 4.1 or higher
                Gradle 6.5
                Libraries
                Viewmodel
                FlowBinding
                Room
                Retrofit
                Moshi
                okhttp-logging-interceptor
                kotlinx.coroutines
                MockWebServer
                Robolectric
                Kotlin coroutines
                Dagger Hilt
                Kotlin Gradle DSL
                License
                This project is released under the Apache License 2.0. See LICENSE for details.

Copyright 2020 Damola Olarewaju All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
