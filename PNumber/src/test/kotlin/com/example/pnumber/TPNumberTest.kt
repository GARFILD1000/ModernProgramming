package com.example.pnumber

import org.junit.Assert.assertEquals
import org.junit.Test

class TPNumberTest {
    companion object{
        const val DELTA = 0.00000001
    }

    @Test
    fun checkConstructors(){
        val pnumber1 = TPNumber(10.0,NumberBase.EIGHT,2)
        assertEquals(10.0, pnumber1.number, DELTA)
        assertEquals(NumberBase.EIGHT, pnumber1.numberBase)
        assertEquals(2, pnumber1.precision)

        val pnumber2 = TPNumber("5.0",NumberBase.SIXTEEN,3)
        assertEquals(5.0, pnumber2.number, DELTA)
        assertEquals(NumberBase.SIXTEEN, pnumber2.numberBase)
        assertEquals(3, pnumber2.precision)
    }

    @Test
    fun checkCopy(){
        val pnumber = TPNumber(10.0, NumberBase.EIGHT, 2)
        val copy = pnumber.copy()
        assertEquals(pnumber.number, copy.number, DELTA)
        assertEquals(pnumber.numberBase, copy.numberBase)
        assertEquals(pnumber.precision, copy.precision)
    }

    @Test
    fun checkSum(){
        val pnumber1 = TPNumber(10.0, NumberBase.EIGHT, 2)
        val pnumber2 = TPNumber(5.0, NumberBase.EIGHT, 2)
        val result = pnumber1 + pnumber2
        val destination = TPNumber(15.0, NumberBase.EIGHT, 2)
        assertEquals(destination.number, result.number, DELTA)
        assertEquals(destination.numberBase, result.numberBase)
        assertEquals(destination.precision, result.precision)
    }

    @Test
    fun checkDiff(){
        val pnumber1 = TPNumber(10.0, NumberBase.EIGHT, 2)
        val pnumber2 = TPNumber(5.0, NumberBase.EIGHT, 2)
        val result = pnumber1 - pnumber2
        val destination = TPNumber(5.0, NumberBase.EIGHT, 2)
        assertEquals(destination.number, result.number, DELTA)
        assertEquals(destination.numberBase, result.numberBase)
        assertEquals(destination.precision, result.precision)
    }

    @Test
    fun checkMul(){
        val pnumber1 = TPNumber(10.0, NumberBase.EIGHT, 2)
        val pnumber2 = TPNumber(-5.0, NumberBase.EIGHT, 2)
        val result = pnumber1 * pnumber2
        val destination = TPNumber(-50.0, NumberBase.EIGHT, 2)
        assertEquals(destination.number, result.number, DELTA)
        assertEquals(destination.numberBase, result.numberBase)
        assertEquals(destination.precision, result.precision)
    }

    @Test
    fun checkDiv(){
        val pnumber1 = TPNumber(-10.0, NumberBase.EIGHT, 2)
        val pnumber2 = TPNumber(5.0, NumberBase.EIGHT, 2)
        val result = pnumber1 / pnumber2
        val destination = TPNumber(-2.0, NumberBase.EIGHT, 2)
        assertEquals(destination.number, result.number, DELTA)
        assertEquals(destination.numberBase, result.numberBase)
        assertEquals(destination.precision, result.precision)
    }

    @Test
    fun checkReverse(){
        val pnumber1 = TPNumber(2.0, NumberBase.EIGHT, 2)
        val result = pnumber1.reverse()
        val destination = TPNumber(0.5, NumberBase.EIGHT, 2)
        assertEquals(destination.number, result.number, DELTA)
        assertEquals(destination.numberBase, result.numberBase)
        assertEquals(destination.precision, result.precision)
    }

    @Test
    fun checkSquare(){
        val pnumber1 = TPNumber(2.0, NumberBase.EIGHT, 2)
        val result = pnumber1.square()
        val destination = TPNumber(4.0, NumberBase.EIGHT, 2)
        assertEquals(destination.number, result.number, DELTA)
        assertEquals(destination.numberBase, result.numberBase)
        assertEquals(destination.precision, result.precision)
    }


}