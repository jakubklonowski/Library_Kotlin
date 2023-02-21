package pl.klonowski.library.services

import pl.klonowski.library.models.*
import retrofit2.Response
import retrofit2.http.*

interface HttpService {

    // books endpoints -----------------------------------------------------------------------------
    @GET("/api/books/{id}")
    suspend fun getBook(@Path("id") id: Int): Response<BookRequest>

    @GET("/api/books")
    suspend fun getBooks(): Response<List<Book>>

    @POST("/api/books")
    suspend fun postBook(@Body bookRequest: BookRequest): Response<BookResponse>

    @PUT("/api/books/{id}")
    suspend fun putBook(@Path("id") id: Int, @Body bookRequest: BookRequest): Response<Any>

    @DELETE("/api/books/{id}")
    suspend fun deleteBook(@Path("id") id: Int): Response<Any>

    // clients endpoints ---------------------------------------------------------------------------
    @GET("/api/clients/{id}")
    suspend fun getClient(@Path("id") id: Int): Response<ClientRequest>

    @GET("/api/clients")
    suspend fun getClients(): Response<List<Client>>

    @POST("/api/clients")
    suspend fun postClient(@Body clientRequest: ClientRequest): Response<ClientResponse>

    @PUT("/api/clients/{id}")
    suspend fun putClient(@Path("id") id: Int, @Body clientRequest: ClientRequest): Response<Any>

    @DELETE("/api/clients/{id}")
    suspend fun deleteClient(@Path("id") id: Int): Response<Any>

    // libraries endpoints -------------------------------------------------------------------------
    @GET("/api/libraries/{id}")
    suspend fun getLibrary(@Path("id") id: Int): Response<LibraryRequestJoin>

    @GET("/api/libraries")
    suspend fun getLibraries(): Response<List<LibraryJoin>>

    @POST("/api/libraries")
    suspend fun postLibrary(@Body libraryRequest: LibraryRequestJoin): Response<LibraryResponse>

    @PUT("/api/libraries/{id}")
    suspend fun putLibrary(@Path("id") id: Int, @Body libraryRequest: LibraryRequestJoin): Response<Any>

    @DELETE("/api/libraries/{id}")
    suspend fun deleteLibrary(@Path("id") id: Int): Response<Any>
}