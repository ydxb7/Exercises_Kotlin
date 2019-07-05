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

package com.example.android.marsrealestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.marsrealestate.network.MarsApi
import com.example.android.marsrealestate.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response


    // TODO (04) Create a coroutine Job and a CoroutineScope using the Main Dispatcher
    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()
    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or the successful number of
     * Mars properties retrieved.
     */
//    private fun getMarsRealEstateProperties() {
//        MarsApi.retrofitService.getProperties().enqueue( object: Callback<List<MarsProperty>> {
//            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//            }
//            override fun onResponse(call: Call<List<MarsProperty>>, response: Response<List<MarsProperty>>) {
//                _response.value = "Success: ${response.body()?.size} Mars properties retrieved"
//            }
//        })
//    }

    private fun getMarsRealEstateProperties() {
        // TODO (05) Call coroutineScope.launch and place the rest of the code in it
        coroutineScope.launch {
            // TODO (06) Call MarsApi.retrofitService.getProperties and call await on the Deferred
            // TODO (07) Surround the Retrofit code with a try/catch, and set _response appropriately
            // Get the Deferred object for our Retrofit request
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                // 因为 Retrofit 已经帮我们把所有的工作都在background完成，所以我们就不用别的thread了。
                // Await the completion of our Retrofit request
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (e: Exception) {
                _response.value = "Failure: ${e.message}"
            }
        }
    }

    // TODO (08) Cancel the Coroutine Job when the ViewModel is finished in onCleared
    /**
     * When the [ViewModel] is finished, we cancel our coroutine [viewModelJob], which tells the
     * Retrofit service to stop.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
