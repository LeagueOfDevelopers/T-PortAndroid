package com.lod.rtviwe.tport.utils

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import timber.log.Timber

data class CollectionJob(private val map: HashMap<String, CompositeJob> = hashMapOf()) {

    fun getJob(name: String): CompositeJob? = map[name]

    fun putJob(name: String) {
        val job = Job()
        map[name] =
            CompositeJob(job, CoroutineScope(Dispatchers.Main + job), CoroutineExceptionHandler { _, exception ->
                Timber.e("Error while working $job: $exception")
            })
    }

    fun putJobs(vararg names: String) = names.forEach { putJob(it) }

    fun clear() = map.forEach { it.value.job.cancel() }
}

data class CompositeJob(
    val job: Job,
    val scope: CoroutineScope,
    val handler: CoroutineExceptionHandler
)