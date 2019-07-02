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
 */

package com.example.android.trackmysleepquality.sleeptracker

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.trackmysleepquality.TextItemViewHolder
import com.example.android.trackmysleepquality.database.SleepNight

// TODO (02) Create SleepNightAdapter class and extend it from RecyclerView.Adapter<TextItemViewHolder>
class SleepNightAdapter: RecyclerView.Adapter<TextItemViewHolder>() {

    // TODO (03) Create a variable, data, that holds a list of SleepNight.
    var data =  listOf<SleepNight>()

    // TODO (04) Override getItemCount() to return the total number of items in the data set.
    override fun getItemCount() = data.size

    // TODO (05) Override onBindViewHolder() and have it update the contents of the
    // ViewHolder to reflect the item at the given position.
    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = data[position]
        holder.textView.text = item.sleepQuality.toString()
    }

    // TODO (06) Override onCreateViewHolder(). We'll complete this method in a later exercise.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}







