package com.example.memory

class TMemory(){
    var fnumber: Number = 0
    var fstate: Boolean = false
    private set

    fun add(e: Number) {
        fnumber += e
        fstate = true
    }

    fun clear() {
        fnumber = 0
        fstate = false
    }

    fun getFState(): String {
        return if (fstate) "On" else "Off"
    }

    operator fun Number.plus(other: Number): Number {
        return when (this) {
            is Long   -> this.toLong() + other.toLong()
            is Int    -> this.toInt()  + other.toInt()
            is Short  -> this.toShort() + other.toShort()
            is Byte   -> this.toByte() + other.toByte()
            is Double -> this.toDouble() + other.toDouble()
            is Float  -> this.toFloat() + other.toFloat()
            else      -> throw RuntimeException("Unknown numeric type")
        }
    }
}