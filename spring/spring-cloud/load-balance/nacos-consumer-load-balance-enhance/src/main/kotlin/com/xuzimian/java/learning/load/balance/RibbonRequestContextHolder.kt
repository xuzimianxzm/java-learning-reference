package com.xuzimian.java.learning.load.balance

object RibbonRequestContextHolder {

    private val holder: ThreadLocal<RibbonRequestContext> = object : ThreadLocal<RibbonRequestContext>() {
        override fun initialValue(): RibbonRequestContext {
            return RibbonRequestContext()
        }
    }

    var currentContext: RibbonRequestContext
        get() = holder.get()
        set(context) {
            holder.set(context)
        }

    fun clearContext() {
        holder.remove()
    }
}