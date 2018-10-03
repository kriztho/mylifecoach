package com.cristhopper.mylifecoach.data.network

import com.cristhopper.mylifecoach.data.interfaces.IEvent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GoogleCalendarService {

    /**
     * @GET declares an HTTP GET request
     * @Path("user") annotation on the userId parameter marks it as a
     * replacement for the {user} placeholder in the @GET path
     */
    @GET("/users/{user}")
    fun getEvents(@Path("user") userId: String): Call<List<IEvent>>
}