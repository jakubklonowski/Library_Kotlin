package pl.klonowski.library.models

data class Client(
    var Id:     Int,
    var Name:   String
) {
    override fun toString(): String {
        return Name
    }
}
