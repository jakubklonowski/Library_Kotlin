package pl.klonowski.library.models

data class Book(
    var Id:     Int,
    var Name:   String,
    var Author: String,
) {
    override fun toString(): String {
        return Name
    }
}
