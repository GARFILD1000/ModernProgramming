package com.example.fraction

import org.junit.Assert.assertEquals
import org.junit.Test

class TFractionEditorTest {
    @Test
    fun checkConstructor(){
        val editor = TFractionEditor()
        assertEquals("0", editor.fraction)
    }

    @Test
    fun checkIsZero() {
        val editor = TFractionEditor()
        assert(editor.isZero())
    }

    @Test
    fun checkAddDigitAndChangeSign() {
        val editor = TFractionEditor()
        editor.addDigit(1)
        editor.changeSign()
        assertEquals(editor.fraction, "-1")
    }

    @Test
    fun checkAddZero() {
        val editor = TFractionEditor()
        editor.addZero()
        assertEquals(editor.fraction, "0")
        editor.addDigit(1)
        editor.addZero()
        assertEquals(editor.fraction, "10")
    }

    @Test
    fun checkRemoveLastDigit() {
        val editor = TFractionEditor()
        editor.addDigit(1)
            .addZero()
            .removeLastDigit()
        assertEquals(editor.fraction, "1")
    }

    @Test
    fun checkClear() {
        val editor = TFractionEditor()
        editor.addDigit(1)
            .addZero()
            .clear()
        assertEquals(editor.fraction, "0")
    }

    @Test
    fun checkAddDivider() {
        val editor = TFractionEditor()
        editor.addDigit(1)
            .addZero()
            .addDivider()
            .addDigit(2)
        assertEquals(editor.fraction, "10/2")
    }

    @Test
    fun checkEditFraction() {
        val editor = TFractionEditor()
        editor.editFraction(0)
        assertEquals(editor.fraction, "-0")
        editor.editFraction(2, 1)
        assertEquals(editor.fraction, "-1")
        editor.editFraction(1)
        assertEquals(editor.fraction, "-10")
        editor.editFraction(3)
        assertEquals(editor.fraction, "-1")
        editor.editFraction(4)
        assertEquals(editor.fraction, "0")
        editor.editFraction(5)
        assertEquals(editor.fraction, "0/")
    }
}