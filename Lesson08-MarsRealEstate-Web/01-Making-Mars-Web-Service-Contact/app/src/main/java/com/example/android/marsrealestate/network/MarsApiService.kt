/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.android.marsrealestate.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://mars.udacity.com/"

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object pointing to the desired URL
 */
// TODO (02) Use Retrofit Builder with ScalarsConverterFactory and BASE_URL
private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

// TODO (03) Implement the MarsApiService interface with @GET getProperties returning a String
interface MarsApiService{
    /**
     * Returns a Retrofit callback that delivers a String
     * The @GET annotation indicates that the "realestate" endpoint will be requested with the GET
     * HTTP method
     */
    // specify the path or endpoint that we want whis method to use. The endpoint for the json
    // response is realestate. When we call the getProperties method, retrofit append the endpoint
    // realestate to the BASE_URL, and creates a call object. The call object is used to start the request.
    @GET("realestate")
    fun getProperties():
            Call<String>
}


// TODO (04) Create the MarsApi object using Retrofit to implement the MarsApiService
/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MarsApi {
    val retrofitService : MarsApiService by lazy { retrofit.create(MarsApiService::class.java) }
}
