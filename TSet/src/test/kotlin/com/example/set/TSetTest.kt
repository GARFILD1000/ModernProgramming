package com.example.set

import org.junit.Assert.assertEquals
import org.junit.Test

class TSetTest {
    @Test
    fun checkConstructor(){
        val set1 = TSet<Int>()
        val set2 = TSet<TFraction>()
        assert(set1.isEmpty())
        assert(set2.isEmpty())
    }

    @Test
    fun checkAddAndContains(){
        val testElements1 = listOf(1,2,3)
        val set1 = TSet<Int>()
        testElements1.forEach{ set1.add(it) }
        for (i in testElements1.indices) {
            assert(set1.contains(testElements1[i]))
        }

        val testElements2 = listOf(
            TFraction(1,1),
            TFraction(1,1)
        )
        val set2 = TSet<TFraction>()
        testElements2.forEach{ set2.add(it) }
        for (i in testElements2.indices) {
            assert(set2.contains(testElements2[i]))
        }
    }

    @Test
    fun checkRemoveAndSize(){
        val testElements1 = mutableListOf(1,2,3)
        val set1 = TSet<Int>()
        testElements1.forEach{ set1.add(it) }
        testElements1.remove(2)
        assertEquals(3, set1.size)
        set1.remove(2)
        for (i in testElements1.indices) {
            assert(set1.contains(testElements1[i]))
        }
        assertEquals(2, set1.size)

        val testElements2 = mutableListOf(
            TFraction(1,1),
            TFraction(2,1),
            TFraction(3,1)
        )
        val set2 = TSet<TFraction>()
        testElements2.forEach{ set2.add(it) }
        val toRemove = testElements2.get(2)
        testElements2.remove(toRemove)
        assertEquals(3, set2.size)
        set2.remove(toRemove)
        for (i in testElements2.indices) {
            assert(set2.contains(testElements2[i]))
        }
        assertEquals(2, set2.size)
    }

    @Test
    fun checkGet(){
        val testElements1 = mutableListOf(1,2,3)
        val set1 = TSet<Int>()
        testElements1.forEach{ set1.add(it) }
        for (i in set1.indices) {
            set1.getElement(i)?.let {
                testElements1.remove(it)
            }
        }
        assert(testElements1.isEmpty())

        val testElements2 = mutableListOf(
            TFraction(1,1),
            TFraction(2,1),
            TFraction(3,1)
        )
        val set2 = TSet<TFraction>()
        testElements2.forEach{ set2.add(it) }

        for (i in set2.indices) {
            set2.getElement(i)?.let {
                testElements2.remove(it)
            }
        }
        assert(testElements1.isEmpty())
    }

    @Test
    fun checkSum() {
        val set1 = TSet<Int>()
        set1.addAll(listOf(1,2,3,4,5,6))
        val set2 = TSet<Int>()
        set2.addAll(listOf(4,5,6,7,8,9))
        val result = set1 + set2
        val destination = listOf(1,2,3,4,5,6,7,8,9)
        result.forEach{
            assert(destination.contains(it))
        }
        destination.forEach{
            assert(result.contains(it))
        }
    }

    @Test
    fun checkDiff() {
        val set1 = TSet<Int>()
        set1.addAll(listOf(1,2,3,4,5,6))
        val set2 = TSet<Int>()
        set2.addAll(listOf(4,5,6,7,8,9))
        val result = set1 - set2
        val destination = listOf(1,2,3)
        result.forEach{
            assert(destination.contains(it))
        }
        destination.forEach{
            assert(result.contains(it))
        }
    }

    @Test
    fun checkMul() {
        val set1 = TSet<Int>()
        set1.addAll(listOf(1,2,3,4,5,6))
        val set2 = TSet<Int>()
        set2.addAll(listOf(4,5,6,7,8,9))
        val result = set1 * set2
        val destination = listOf(4,5,6)
        result.forEach{
            assert(destination.contains(it))
        }
        destination.forEach{
            assert(result.contains(it))
        }
    }

}