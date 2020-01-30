package com.example.fraction

import org.junit.Assert.assertEquals
import org.junit.Test

class TFractionTest {
    companion object{
        const val DELTA = 0.00000001
    }

    @Test
    fun checkConstructors() {
        val numerator = -180L
        val denominator = -18L
        val string = "$numerator / $denominator"
        val fraction1 = TFraction(numerator, denominator)
        val fraction2 = TFraction(string)
        val destination = TFraction(10L, 1L)
        assertEquals(destination.numerator, fraction1.numerator)
        assertEquals(destination.numerator, fraction2.numerator)
        assertEquals(destination.denominator, fraction1.denominator)
        assertEquals(destination.denominator, fraction2.denominator)
    }

    @Test
    fun checkCopy() {
        val fraction = TFraction(1L, 25L)
        val copy = fraction.copy()
        assertEquals(fraction.numerator, copy.numerator)
        assertEquals(fraction.denominator, copy.denominator)
    }

    @Test
    fun checkSum(){
        val fraction1 = TFraction(2,3)
        val fraction2 = TFraction(3,4)
        val result = fraction1 + fraction2
        val destination = TFraction(17, 12)
        assertEquals(destination.numerator, result.numerator)
        assertEquals(destination.denominator, result.denominator)
    }

    @Test
    fun checkDiff(){
        val fraction1 = TFraction(2,3)
        val fraction2 = TFraction(3,4)
        val result = fraction1 - fraction2
        val destination = TFraction(-1, 12)
        assertEquals(destination.numerator, result.numerator)
        assertEquals(destination.denominator, result.denominator)
    }

    @Test
    fun checkMul(){
        val fraction1 = TFraction(2,3)
        val fraction2 = TFraction(3,4)
        val result = fraction1 * fraction2
        val destination = TFraction(1, 2)
        assertEquals(destination.numerator, result.numerator)
        assertEquals(destination.denominator, result.denominator)
    }

    @Test
    fun checkDiv(){
        val fraction1 = TFraction(2,3)
        val fraction2 = TFraction(3,4)
        val result = fraction1 / fraction2
        val destination = TFraction(8, 9)
        assertEquals(destination.numerator, result.numerator)
        assertEquals(destination.denominator, result.denominator)
    }

    @Test
    fun checkSquare(){
        val fraction1 = TFraction(3,5)
        val result = fraction1.square()
        val destination = TFraction(9, 25)
        assertEquals(destination.numerator, result.numerator)
        assertEquals(destination.denominator, result.denominator)
    }

    @Test
    fun checkReverse(){
        val fraction1 = TFraction(-2,3)
        val result = fraction1.reversed()
        val destination = TFraction(-3, 2)
        assertEquals(destination.numerator, result.numerator)
        assertEquals(destination.denominator, result.denominator)
    }

    @Test
    fun checkMinus(){
        val fraction1 = TFraction(-2,3)
        val result = fraction1.getNegative()
        val destination = TFraction(2, 3)
        assertEquals(destination.numerator, result.numerator)
        assertEquals(destination.denominator, result.denominator)
    }

    @Test
    fun checkEquals(){
        val fraction1 = TFraction(2,3)
        val fraction2 = TFraction(4,6)
        assert(fraction1 == fraction2)
    }

    @Test
    fun checkMore(){
        val fraction1 = TFraction(3,3)
        val fraction2 = TFraction(2,3)
        assert(fraction1 > fraction2)
    }

    @Test
    fun checkLess(){
        val fraction1 = TFraction(3,3)
        val fraction2 = TFraction(2,3)
        assert(fraction2 < fraction1)
    }
}