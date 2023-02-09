## README

## Introduction
This is a project using MVVM architecture pattern with Room library and RxAndroid. Project performs CRUD functions for USER table and contains various modules for activities, adapters, database, listeners, repository, and viewmodel.

## Libraries Used
Project is created with:
* Lifecycle Extensions: androidx.lifecycle:lifecycle-extensions:2.2.0
* Room runtime: androidx.room:room-runtime:2.3.0
* Room compiler: androidx.room:room-compiler:2.3.0
* Room testing: androidx.room:room-testing:2.3.0
* RxJava: io.reactivex.rxjava2:rxandroid:2.0.1

	
## Project Structure
* activities: contains LoginActivity, SignupActivity, and MainActivity.
* adapters: contains UserAdapter.
* database: contains AppDatabase, UserModel, and UserDao.
* listeners: contains UserOnClickListener.
* repository: contains UserRepository.
* viewmodel: contains UserViewModel.


## Conclusion
This project implements the MVVM architecture pattern using Room and RxAndroid to manage the data and handle background requests. The project structure is organized in a clear and maintainable way.