package com.example.pnumber

import junit.framework.Assert.assertEquals
import org.junit.Test

class TPNumberEditorTest {
    @Test
    fun checkConstructor(){
        val editor = TPNumberEditor()
        assertEquals(editor.number, TPNumberEditor.ZERO)
    }

    @Test
    fun checkIsZero(){
        val editor = TPNumberEditor()
        assert(editor.isZero())
        editor.addDigit(1)
        assert(!editor.isZero())
    }

    @Test
    fun checkChangeSign() {
        val editor = TPNumberEditor()
        editor.addDigit(1)
        assertEquals("1", editor.number)
        editor.changeSign()
        assertEquals("-1", editor.number)
    }

    @Test
    fun checkAddZero(){
        val editor = TPNumberEditor()
        editor.addDigit(1)
        editor.addZero()
        assertEquals("10", editor.number)
    }

    @Test
    fun checkRemoveLastDigit(){
        val editor = TPNumberEditor()
        editor.changeSign()
        editor.addDigit(1)
        editor.addZero()
        editor.removeLastDigit()
        assertEquals("-1", editor.number)
        editor.removeLastDigit()
        assertEquals("-0", editor.number)
    }

    @Test
    fun checkClear(){
        val editor = TPNumberEditor()
        editor.changeSign()
        editor.addDigit(1)
        editor.addZero()
        editor.clear()
        assertEquals("0", editor.number)
    }

    @Test
    fun checkAddDivider(){
        val editor = TPNumberEditor()
        editor.changeSign()
        editor.addDigit(1)
        editor.addDivider()
        assertEquals("-1.", editor.number)
    }

    @Test
    fun checkEditNumber(){
        val editor = TPNumberEditor()
        assertEquals("0", editor.number)
        editor.editNumber(0)
        assertEquals("-0", editor.number)
        editor.editNumber(1)
        assertEquals("-0", editor.number)
        editor.editNumber(2, 1)
        assertEquals("-1", editor.number)
        editor.editNumber(3)
        assertEquals("-0", editor.number)
        editor.editNumber(4)
        assertEquals("0", editor.number)
        editor.editNumber(2, 1)
        editor.editNumber(5)
        assertEquals("1.", editor.number)
    }
}