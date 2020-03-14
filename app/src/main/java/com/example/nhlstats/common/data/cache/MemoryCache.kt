package com.example.nhlstats.common.data.cache

interface MemoryCache<K> {

    operator fun <T> get(key: K): T?

    fun <T> getAll(): List<T>

    fun contains(key: K): Boolean

    operator fun set(key: K, value: Any)

    fun <T> remove(key: K): T?

    fun clear()

    companion object {
        val EMPTY = object : MemoryCache<Any> {
            override fun <T> get(key: Any): T? = null

            override fun <T> getAll(): List<T> = emptyList()


            override fun contains(key: Any): Boolean = false

            override fun set(key: Any, value: Any) = Unit

            override fun <T> remove(key: Any): T? = null

            override fun clear() = Unit
        }
    }
}