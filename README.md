# Ulesson-TakeHomeTest


#ULesson test
Access learning resources.
Summary
The app pulls subject/media content from uLesson API. It is built according to the Model-View-ViewModel(MVVM) architecture.

#Architecture
The application follows clean architecture because of the benefits it brings to software which includes scalability, maintainability and testability. It enforces separation of concerns and dependency inversion, where higher and lower level layers all depend on abstractions. In the project, the layers are separated into different gradle modules namely:

Domain
Data
Remote
Cache
These modules are Kotlin modules except the cache module. The reason being that the low level layers need to be independent of the Android framework. One of the key points of clean architecture is that low level layers should be platform agnostic. As a result, the domain, data and presentation layers can be plugged into a kotlin multiplatform project for example, and it will run just fine because we don't depend on the android framework. The cache and remote layers are implementation details that can be provided in any form (Firebase, GraphQl server, REST, ROOM, SQLDelight, etc) as long as it conforms to the business rules / contracts defined in the data layer which in turn also conforms to contracts defined in domain. The project has one feature module dashboard that holds the UI code and presents data to the users. The main app module does nothing more than just tying all the layers of our app together.

For dependency injection and asynchronous programming, the project uses Dagger Hilt and Coroutines with Flow. Dagger Hilt is a fine abstraction over the vanilla dagger boilerplate, and is easy to setup. Coroutines and Flow brings kotlin's expressibility and conciseness to asynchronous programming, along with a fine suite of operators that make it a robust solution.

#Presentation
As stated earlier, the presentation layer is implemented with MVI architecture. The project has a kotlin module called presentation which defines the contract that presenters should implement. The layer is also platform agnostic, making it easy to change implementation details (ViewModel, etc).

MVI architecture has two main components - The model and the view, everything else is the data that flows between these two components. The view state comes from the Model and goes into the View for rendering. Intents come from the View and are consumed by the Model for processing. As a result, the data flow is unidirectional.

The project contains a class called State machine which encapsulates logic of how to process intents and make new view state. It relies on an intent processor that takes intents from the view, liases with a third-party (in this case our domain layer) to process the intent and then returns a result. A view state reducer takes in the result and the previous state to compute a new view state. The views (fragments/components) output intents and take state as input.

The Viewmodel which is the presenter implementation is very lean, depending solely on the state machine. The reason for using the Jetpack Viewmodel is that it survives configuration changes, and thus ensures that the view state is persisted across screen rotation.

MVI is a good architecture to use when you don't want any surprises in user experience as state only comes from one source and is immutable. On the other hand, it does come with a lot of boilerplate. Thankfully, there are a couple of libraries that abstract the implementation details (Mosby, FlowRedux, MvRX) and make it a lot easier to use. This case study has more of a bare bones implementation which represents the core concepts of MVI.

State rendering
For each screen, there is a sealed class of View state, View intent and results. It's also possible to want to render multiple view states in one screen, leading to the use of view components. View components are reusable UI components that extend a viewGroup, which knows how to render its own view state and send intents. For example, the dashboard screen has two components - SubjectsView and RecentTopicsView. The DashboardFragment then passes state to these components to render on the screen. It also takes intents from the components to process. These views encapsulate logic for rendering success, error and empty states. The data for the views are fetched concurrently, allowing any of the views to render whenever its data is available. It also allows the user to retry the data fetch for each individual component if it fails. The state for each of view is decoupled from one another and is cached in a Flow persisted in the Fragment's Viewmodel.

#Domain
The domain layer contains the app business logic. It defines contracts for data operations and domain models to be used in the app. All other layers have their own representation of these domain models, and Mapper classes (or adapters) are used to transform the domain models to each layer's domain model representation. Usecases which represent a single unit of business logic are also defined in the domain layer, and are consumed by the presentation layer. Writing mappers and models can take a lot of effort and result in boilerplate, but they make the codebase much more maintainable and robust by separating concerns.

#Data
The Data layer implements the contract for providing data defined in the domain layer, and it in turn provides a contract that will be used to fetch data from the remote and cache data sources. We have two data sources - Remote and Cache. Remote relies on Retrofit library to fetch data from the uLesson.com REST api, while the cache layer uses Room library to persist the search history.

Features
Clean Architecture with MVI (Uni-directional data flow)
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

Copyright 2020 Zizoh James Anto. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
