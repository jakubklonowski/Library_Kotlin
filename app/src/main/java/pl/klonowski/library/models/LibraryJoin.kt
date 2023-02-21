package pl.klonowski.library.models

data class LibraryJoin(
    var Library: Library,
    var Book:    Book,
    var Client:  Client
)
