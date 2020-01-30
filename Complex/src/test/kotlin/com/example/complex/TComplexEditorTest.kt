package com.example.complex

import org.junit.Assert.assertEquals
import org.junit.Test

class TComplexEditorTest {
    @Test
    fun checkConstructor(){
        val editor = TComplexEditor()
        assertEquals("0", editor.complex)
    }

    @Test
    fun checkAddDivider(){
        val editor = TComplexEditor()
        editor.addDivider()
        assertEquals("0 + 0i", editor.complex)
    }

    @Test
    fun checkAddDigitAndZero(){
        val editor = TComplexEditor()
        editor.addDigit(1)
        editor.addZero()
        assertEquals("10", editor.complex)
        editor.addDivider()
        editor.addDigit(2)
        editor.addDigit(3)
        assertEquals("10 + 23i", editor.complex)
    }

    @Test
    fun checkChangeSign(){
        val editor = TComplexEditor()
        editor.addDigit(1)
        editor.addZero()
        editor.changeSign()
        assertEquals("-10", editor.complex)
        editor.addDivider()
        editor.addDigit(2)
        editor.addDigit(3)
        editor.changeSign()
        assertEquals("-10 + -23i", editor.complex)
    }

    @Test
    fun checkRemoveLast(){
        val editor = TComplexEditor()
        editor.addDigit(1)
            .addZero()
            .addDigit(2)
            .removeLastDigit()
        assertEquals("10", editor.complex)
        editor.addDivider()
            .addDigit(2)
            .addDigit(3)
            .removeLastDigit()
        assertEquals("10 + 2i", editor.complex)
        editor.removeLastDigit()
        assertEquals("10 + 0i", editor.complex)
        editor.removeLastDigit()
        assertEquals("10", editor.complex)
    }

    @Test
    fun checkClear(){
        val editor = TComplexEditor()
        editor.addDigit(1)
            .addZero()
            .addDigit(2)
            .clear()
        assertEquals("0", editor.complex)
    }

    @Test
    fun checkEditComplex(){
        val editor = TComplexEditor()
        editor.editComplex(0)
        assertEquals("-0", editor.complex)
        editor.editComplex(2,1)
        assertEquals("-1", editor.complex)
        editor.editComplex(1)
        assertEquals("-10", editor.complex)
        editor.editComplex(3)
        assertEquals("-1", editor.complex)
        editor.editComplex(5)
        assertEquals("-1 + 0i", editor.complex)
        editor.editComplex(4)
        assertEquals("0", editor.complex)
    }
}