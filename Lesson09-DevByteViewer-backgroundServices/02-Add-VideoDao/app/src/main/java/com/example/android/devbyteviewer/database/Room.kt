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

package com.example.android.devbyteviewer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// TODO (01) Create a @Dao interface called VideoDao.
@Dao
interface VideoDao {
    // TODO (02) Add SQL @Query getVideos() function that returns a List of DatabaseVideo.
    @Query("select * from databasevideo")
    fun getVideos(): List<DatabaseVideo>

    // TODO (03) Add SQL @Insert insertAll() that replaces on conflict (or upsert).
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg videos: DatabaseVideo)  // vararg: variable arguments. The function can
    // take an unknown number of arguments. It'll actually pass an array under the hood and this way
    // callers can pass a few videos without making a list.
}


