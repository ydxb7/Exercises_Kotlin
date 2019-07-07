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

package com.example.android.devbyteviewer

import android.app.Application
import android.os.Build
import androidx.work.*
import com.example.android.devbyteviewer.work.RefreshDataWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Override application to setup background work via WorkManager
 */
class DevByteApplication : Application() {

    // TODO (01) Create CoroutineScope variable applicationScope, using Dispatchers.Default.
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    // TODO (02) Create a delayedInit() function that calls setupRecurringWork() in
    // the coroutine you defined above.
    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    // TODO (04) Create a setupRecurringWork() function and use a Builder to define a
    // repeatingRequest variable to handle scheduling work.
    private fun setupRecurringWork(){
        // TODO (07) In setupRecurringWork(), define constraints to prevent work from occurring when
        // there is no network access or the device is low on battery.
        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.UNMETERED)  // 不要花钱的网络
                .setRequiresBatteryNotLow(true)   // 不在低电量时
                .setRequiresCharging(true)      // 正在充电时
                .apply {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        setRequiresDeviceIdle(true)   // 设备空闲时（用户不在用手机）
                    }
                }.build()

        // TODO (08) Add the constraints to the repeatingRequest definition.
        // 每天跑1次
        val repeatingRequest = PeriodicWorkRequestBuilder<RefreshDataWorker>(
                1,
                TimeUnit.DAYS
        ).setConstraints(constraints).build()

        // TODO (05) In setupRecurringWork(), get an instance of WorkManager and
        // launch call enqueuPeriodicWork() to schedule the work.
        WorkManager.getInstance().enqueueUniquePeriodicWork(
                RefreshDataWorker.WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,  // 如果同时有2个work开始，则保留第一个，丢弃第二个
                repeatingRequest    // 每天1次
        )
    }

    /**
     * onCreate is called before the first screen is shown to the user.
     *
     * Use it to setup any background tasks, running expensive setup operations in a background
     * thread to avoid delaying app start.
     */
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        // TODO (03) Call delayedInit().
        delayedInit()
    }
}
