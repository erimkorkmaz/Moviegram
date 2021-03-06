# Moviegram
This is my internship project to enhance my programmming skills and learn a lot about some Android concepts 
such as Retrofit, Room, MVVM architecture.

<img src="Screenshots/Screenshot_1564637632.png" width="200" height="400" /> <img src="Screenshots/Screenshot_1564636929.png" width="200" height="400" /> <img src="Screenshots/Screenshot_1564636933.png" width="200" height="400" /> <img src="Screenshots/Screenshot_1564636951.png" width="200" height="400" />

# Libraries
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)

- [Retrofit](https://square.github.io/retrofit/)

- [Room](https://developer.android.com/jetpack/androidx/releases/room/)

- [Glide](https://github.com/bumptech/glide/)

- [Lottie](https://github.com/airbnb/lottie-android/)

- [Reactive Network](https://github.com/pwittchen/ReactiveNetwork)

- [Stetho](http://facebook.github.io/stetho/)

- [Styleable Toast](https://github.com/Muddz/StyleableToast)

- [Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)

# Structure 

Moviegram written by Kotlin Language and uses MVVM architecture.

## Submission

### - Explain the project in general and how to run it.

Moviegram uses [TheMovieDB Api](https://www.themoviedb.org/documentation/api). It shows you the now playing movies in three different screens.
There are Now Playing and Favorites sections bottom on the main screen. The other step is when you click on a movie, it takes the selected movie id 
and gets the detail of that movie. Moreover, when you click favorite button on movie detail screen, it saves the movie to favorites list 
and you can see them in favorites screen. Therefore, movies, you like or want to watch, is showed on favorites screen and you can easily modify it.

### - Why you have selected that software architecture?

Moviegram uses the Android MVVM architecture. To promote both of this project's orientations and avoid possible memory leaks or strange behaviors, the knowledge of the lifecycle is very necessary. The ViewModel component takes care of that responsibility via 
its lifecycle owner(Activities or Fragments).

### - What would you change if you had more time?

If I had more time to research and implement, I would like to use some libraries to my project such as
- [Dagger2](https://dagger.dev/)

- [Rxjava](https://github.com/ReactiveX/RxJava)

- [Coroutine](https://developer.android.com/kotlin/coroutines)

- [Data Binding](https://developer.android.com/topic/libraries/data-binding/)

- [Paging Library](https://developer.android.com/topic/libraries/architecture/paging/)

### - What to do to make app production ready? 

- Unit and UI tests of the project should be written before publishing
- [Proguard](https://developer.android.com/studio/build/shrink-code) should be used  in order to minify, obfuscate, and optimize the code.








