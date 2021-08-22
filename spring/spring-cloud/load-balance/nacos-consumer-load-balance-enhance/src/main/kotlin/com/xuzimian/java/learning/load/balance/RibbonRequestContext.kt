package com.xuzimian.java.learning.load.balance

class RibbonRequestContext {
    private val attr: MutableMap<String, String> = HashMap()

    fun put(key: String, value: String): String? {
        return attr.put(key, value)
    }

    fun remove(key: String): String? {
        return attr.remove(key)
    }

    operator fun get(key: String): String? {
        return attr[key]
    }
}