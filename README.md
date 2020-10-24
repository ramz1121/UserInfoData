# User Info

A simple application to display user information and display user image. 

## Architecture 

 * MVVM architecture is used along with the repository layer
 * Base classes(Base Activity,Base ViewModel, Base Adapter, BaseItemViewHolder, BaseItemViewModel) layer added to make the code more concise.
 * RxJava is used for asynchronous call, provided with Observables, Schedulers.
 * Followed SOLID principles.
 * Recycler view is used to populate list items.
 * Coroutines for suspended execution.
 * Dagger 2 is used for dependency injection.
 * Mockito and Junit are used for Unit testing.
 * Espresso for UI testing
 * Picasso used for image downloading and caching

## Features
 
 * User information populated.
 * User information display along with image.