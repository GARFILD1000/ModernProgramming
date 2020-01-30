package com.example.memory

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.system.exitProcess

class TProcTest {
    @Test
    fun checkConstructor(){
        val processor = TProc()
        assertEquals(null, processor.lopRes)
        assertEquals(null, processor.rop)
    }

    @Test
    fun checkReset(){
        val processor = TProc()
        processor.rop = TFraction(15L, 0L)
        processor.lopRes = TFraction(10L, 0L)
        processor.operation = Operation.Add
        processor.reset()
        assertEquals(null, processor.lopRes)
        assertEquals(null, processor.rop)
        assertEquals(Operation.None, processor.operation)
    }

    @Test
    fun checkClear(){
        val processor = TProc()
        processor.operation = Operation.Add
        processor.clearOperation()
        assertEquals(Operation.None, processor.operation)
    }

    @Test
    fun checkRunOperation(){
        val processor = TProc()
        val operation = Operation.Add
        val rop = TFraction(15L, 1L)
        processor.rop = rop
        processor.operation = operation
        processor.lopRes = TFraction(10L, 1L)
        processor.runOperation()
        val result = TFraction(25L, 1L)
        assertEquals(result, processor.lopRes)
        assertEquals(rop, processor.rop)
        assertEquals(operation, processor.operation)
    }

    @Test
    fun checkRunFunction() {
        val processor = TProc()
        val operation = Operation.Add
        val rop = TFraction(2L, 1L)
        processor.rop = rop
        processor.runFunction(Function.Sqr)
        val result = TFraction(4L, 1L)
        assertEquals(result, processor.rop)
    }
}