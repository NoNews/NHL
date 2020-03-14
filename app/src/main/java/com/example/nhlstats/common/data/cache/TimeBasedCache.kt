package com.example.nhlstats.common.data.cache

import android.util.LruCache
import java.util.concurrent.TimeUnit

open class TimeBasedCache<K>(private val lifeTimeMillis: Long, maxSize: Int) : MemoryCache<K> {

    private val lru = LruCache<K, ValueAndTime>(maxSize)

    override fun <T> getAll(): List<T> = lru.snapshot().values.extractNotExpiredValues()

    override fun set(key: K, value: Any) {
        lru.put(key, ValueAndTime(value))
    }


    override fun <T> remove(key: K): T? = lru.remove(key) as? T

    override fun <T> get(key: K): T? {
        val cached: ValueAndTime = lru[key] ?: return null
        if (isExpired(cached.creationTimeMillis, lifeTimeMillis)) {
            return null
        }
        return cached.value as? T
    }

    override fun contains(key: K): Boolean = get<K>(key) != null

    override fun clear() {
        lru.evictAll()
    }


    private fun <T> Iterable<ValueAndTime>.extractNotExpiredValues() =
        asSequence().filterNot {
            isExpired(
                it.creationTimeMillis,
                lifeTimeMillis
            )
        }.map { it.value }.toList() as List<T>

    private data class ValueAndTime(
        val value: Any,
        val creationTimeMillis: Long = System.currentTimeMillis()
    )

    private fun isExpired(creationTimeMillis: Long, lifeTimeMillis: Long) =
        System.currentTimeMillis() - creationTimeMillis > lifeTimeMillis

    companion object {
        fun <K> minutes(duration: Long, size: Int): TimeBasedCache<K> =
            TimeBasedCache(TimeUnit.MINUTES.toMillis(duration), size)

    }
}