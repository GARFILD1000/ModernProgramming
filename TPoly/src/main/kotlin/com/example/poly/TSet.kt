package com.example.poly

import java.util.*

class TSet<T> : HashSet<T>(){

    override fun clear() {
        super.clear()
    }

    override fun add(element: T): Boolean {
        return if (!contains(element)) super.add(element) else false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var removedSomething = false
        for (element in elements) {
            val result = remove(element)
            removedSomething = removedSomething or result
        }
        return removedSomething
    }

    override fun remove(element: T): Boolean {
        return super.remove(element)
    }

    override fun isEmpty(): Boolean {
        return super.isEmpty()
    }

    override fun contains(element: T): Boolean {
        return super.contains(element)
    }

    fun getSum(elements: TSet<T>): TSet<T> {
        val newElements = TSet<T>()
        for (element in this) {
            newElements.add(element)
        }
        for (element in elements) {
            if (!newElements.contains(element)) {
                newElements.add(element)
            }
        }
        return newElements
    }


    fun getDiff(elements: TSet<T>): TSet<T> {
        val newElements = TSet<T>()
        for (element in this) {
            if (!elements.contains(element)) {
                newElements.add(element)
            }
        }
        return newElements
    }

    fun getMul(elements: TSet<T>): TSet<T> {
        val newElements = TSet<T>()
        for (element in this) {
            if (elements.contains(element)) {
                newElements.add(element)
            }
        }
        return newElements
    }

    operator fun plus(other: TSet<T>): TSet<T> = getSum(other)
    operator fun minus(other: TSet<T>): TSet<T> = getDiff(other)
    operator fun times(other: TSet<T>): TSet<T> = getMul(other)

    fun getElement(idx: Int): T? {
        var item: T? = null
        if (idx < size) {
            this.forEachIndexed { index, value ->
                if (index == idx) {
                    item = value
                }
            }
        }
        return item
    }
}

fun main(){
    val set1 = TSet<Int>()
    set1.addAll(listOf(1,2,3,4,5,6))
    val set2 = TSet<Int>()
    set2.addAll(listOf(4,5,6,7,8,9))
    print((set1 * set2).joinToString(" "))
}