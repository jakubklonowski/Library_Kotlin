package pl.klonowski.library.models

data class LibraryRequestJoin(
    var Library: LibraryRequest,
    var Book:    Book,
    var Client:  Client
)
