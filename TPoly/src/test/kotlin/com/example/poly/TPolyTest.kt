package com.example.poly

import org.junit.Assert.assertEquals
import org.junit.Test

class TPolyTest {
    companion object{
        const val DELTA = 0.000000000000001
    }

    @Test
    fun checkConstructorAndGetters() {
        val poly1 = TPoly(6, 3)
        assertEquals(TMember(6,3), poly1.getElement(0))
        assertEquals(3, poly1.getMaxDegree())
        assertEquals(6, poly1.getRatio(3))
        assertEquals("6*x^3", poly1.getElement(0).toString())


        val poly2 = TPoly(1, 3)
        assertEquals(TMember(1,3), poly2.getElement(0))
        assertEquals(3, poly2.getMaxDegree())
        assertEquals(1, poly2.getRatio(3))
        assertEquals("x^3", poly2.getElement(0).toString())
    }

    @Test
    fun checkClear() {
        val poly = TPoly(4, 5)
        poly.clear()
        assertEquals(0, poly.getMaxDegree())
        assertEquals(0, poly.getRatio(0))
    }

    @Test
    fun checkSum() {
        val poly1 = TPoly(3, 2) + TMember(-2, 1) + TMember(7, 0)
        val poly2 = TPoly(5, 2) + TMember(4, 1) + TMember(-3, 0)
        val result = poly1 + poly2

        assertEquals(TMember(8, 2), result.getElement(0))
        assertEquals(TMember(2, 1), result.getElement(1))
        assertEquals(TMember(4, 0), result.getElement(2))
    }

    @Test
    fun checkDiff(){
        val poly1 = TPoly(3, 2) + TMember(-2, 1) + TMember(7, 0)
        val poly2 = TPoly(5, 2) + TMember(4, 1) + TMember(-3, 0)
        val result = poly1 - poly2

        assertEquals(TMember(-2, 2), result.getElement(0))
        assertEquals(TMember(-6, 1), result.getElement(1))
        assertEquals(TMember(10, 0), result.getElement(2))
    }

    @Test
    fun checkMul(){
        val poly1 = TPoly(3, 2) + TMember(-2, 1) + TMember(7, 0)
        val poly2 = TPoly(5, 2) + TMember(4, 1) + TMember(-3, 0)
        val result = poly1 * poly2

        assertEquals(TMember(15, 4), result.getElement(0))
        assertEquals(TMember(2, 3), result.getElement(1))
        assertEquals(TMember(18, 2), result.getElement(2))
        assertEquals(TMember(34, 1), result.getElement(3))
        assertEquals(TMember(-21, 0), result.getElement(4))
    }

    @Test
    fun checkEquals() {
        assert(TPoly(5,2) == TPoly(5,2))
        assert(TPoly(5,2) != TPoly(4,2))
        assert((TPoly(3,1) + TMember(5,2))
                == (TPoly(5,2) + TMember(3,1)))
    }

    @Test
    fun checkDifferentiate() {
        val poly = TPoly(1,3) + TMember(7,2) + TMember(5,1) + TMember(2,0)
        val result = poly.differentiate()
        assertEquals(TMember(3,2), result.getElement(0))
        assertEquals(TMember(14,1), result.getElement(1))
        assertEquals(TMember(5,0), result.getElement(2))
    }

    @Test
    fun checkCalculate() {
        val poly = TPoly(1,3) + TMember(2,2)
        val result = poly.calculate(2.0)
        assertEquals(16.0, result, DELTA)
    }
}