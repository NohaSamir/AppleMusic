# Displays the top 100 music albums in the US

List of top 100 albums with album name, artists and an album photo cover. After first fetching of the remote data it is stored in local storage and after the next launches of the app it is shown immediately and after successful response from the server the data is update in local storage and it is updated on the main screen as well. 

### Details screen also shows:
Photo cover
Album name
Artists
Genres names
Release date
Copyright info
Button with the ability to navigation to album on browser

-----
# What's inside?

- MVI pattern
- Clean Architecture
- kotlin Composet for Ul design
- Hilt for dependency injection
- Realm for local storage
- Retrofit for RESTfUl APIs

----
# How is it structured?

**App Layer (Presentation Layer)**: This layer is responsible for handling the user interface (UI) and the associated business logic, which is managed by the ViewModels.


**Domain Layer**: This layer is responsible for handling the core business logic and domain-specific rules. It contains entities, use cases, and repository interfaces that encapsulate the essential business processes.

**Data Layer**: This layer is responsible for managing data operations. It acts as a bridge between the domain layer and the data sources, such as remote services and local databases. It includes repositories that implement the repository interfaces defined in the domain layer.

**Remote Layer**: This sub-layer of the data layer handles data operations involving remote data sources, such as APIs or web services. It includes network calls, data transfer objects (DTOs), and remote data source implementations.

**Cache Layer**: This sub-layer of the data layer is responsible for managing local data storage, such as databases and data store. It includes data access objects (DAOs), cache data source implementations, and mechanisms for data synchronization.

----

# Screenshots 

<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/b49dec57-aac6-4e28-8d51-411c2948fae8" width="300">
<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/da967b20-1f65-4f90-98fe-e459d02eb506" width="300">
<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/6d7d644b-4c24-4ac9-804e-188d0bf68d68" width="300">
<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/fc22dc27-d06f-4943-a3b1-69348a9d76df" width="300">
<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/0fb4beab-e19e-4aa5-b8dc-126129c37594" width="300">
<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/cad8471e-8cc8-45e2-b5aa-21b28de56294" width="300">
<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/1c8ff931-fab7-4afa-b0f0-15cf210af72a" width="300">
<img src="https://github.com/NohaSamir/AppleMusic/assets/25500250/a86aea45-9cb2-4a16-aa61-f52818ccae6f" width="600">


Landscape & Portrait video
https://github.com/NohaSamir/AppleMusic/assets/25500250/5f0a847a-c598-4e94-8502-8c6eb879b319

No Internet Connection video
https://github.com/NohaSamir/AppleMusic/assets/25500250/930ca067-89f9-4fd9-a2e3-7df897cfe44d
