mvvm-kotlin
===========

A clean Model-View-ViewModel (MVVM) architecture sample written in Kotlin.

The project achieves following features:
Implemented :
1) First screen shows the list of git users depending on search keyword entered by the user.
2) User details screen is shown upon clicking items in the list.
3) User search history is implemented on the first screen.

Not implemented:
Pagination

Architecture and code approach :
1) Project uses clean architecture principles where the ui folder is for presentation of data.
2) Domain folder is for business logic.
3) Data layer is for getting data from remote source or local source.
4) Project uses android architecture components like vievmodel, live data as recommended by Google.
5) Unit tests are implemented for the respective features.
6) Use cases are implemented for adding new features in a scalable way.

Architecture
------------

