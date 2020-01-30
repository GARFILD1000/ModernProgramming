package com.example.complex

import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.math.PI

class TComplexTest {
    companion object{
        const val DELTA = 0.0000000000001
    }

    @Test
    fun checkStringConstructor(){
        val complexReal = 1.0
        val complexImage = -2.0
        val complexString = "$complexReal + ${complexImage}i"
        val complex = TComplex(complexString)
        assertEquals(complex.real, 1.0, DELTA)
        assertEquals(complex.image, -2.0, DELTA)
    }

    @Test
    fun checkNumbersConstructor(){
        val complexReal = 1.0
        val complexImage = -2.0
        val complex = TComplex(complexReal, complexImage)
        assertEquals(complex.real, complexReal, DELTA)
        assertEquals(complex.image, complexImage, DELTA)
    }

    @Test
    fun checkSum(){
        val complex1 = TComplex(1.0, -2.0)
        val complex2 = TComplex(2.0, -1.0)
        val result = complex1 + complex2
        val destination = TComplex(3.0, -3.0)
        assertEquals(destination.real, result.real, DELTA)
        assertEquals(destination.image, result.image, DELTA)
    }

    @Test
    fun checkDiff(){
        val complex1 = TComplex(2.0, -1.0)
        val complex2 = TComplex(-1.0, 2.0)
        val result = complex1 - complex2
        val destination = TComplex(3.0, -3.0)
        assertEquals(destination.real, result.real, DELTA)
        assertEquals(destination.image, result.image, DELTA)
    }

    @Test
    fun checkMul(){
        val complex1 = TComplex(2.0, 3.0)
        val complex2 = TComplex(-1.0, 1.0)
        val result = complex1 * complex2
        val destination = TComplex(-5.0, -1.0)
        assertEquals(destination.real, result.real, DELTA)
        assertEquals(destination.image, result.image, DELTA)
    }

    @Test
    fun checkDiv(){
        val complex1 = TComplex(2.0, 3.0)
        val complex2 = TComplex(-1.0, 4.0)
        val result = complex1 / complex2
        val destination = TComplex(10.0/17.0, -11.0/17.0)
        assertEquals(result.real, destination.real, DELTA)
        assertEquals(result.image, destination.image, DELTA)
    }

    @Test
    fun checkSquare(){
        val complex = TComplex(3.0, 5.0)
        val result = complex.square()
        val destination = TComplex(-16.0, 30.0)
        assertEquals(result.real, destination.real, DELTA)
        assertEquals(result.image, destination.image, DELTA)
    }

    @Test
    fun checkInverse(){
        val complex = TComplex(-2.0, 8.0)
        val result = complex.inverse()
        val destination = TComplex(-2.0 / 68.0, -8.0 / 68.0)
        assertEquals(result.real, destination.real, DELTA)
        assertEquals(result.image, destination.image, DELTA)
    }

    @Test
    fun checkModule() {
        val complex = TComplex(6.0, 8.0)
        val result = complex.module()
        val destination = 10.0
        assertEquals(result, destination, DELTA)
    }

    @Test
    fun checkArgument(){
        val complex1 = TComplex(0.0, 0.0)
        val result1 = complex1.argument()
        val destination1 = -1.0
        assertEquals(result1, destination1, DELTA)

        val complex2 = TComplex(0.0, 1.0)
        val result2 = complex2.argument()
        val destination2 = PI / 2.0
        assertEquals(result2, destination2, DELTA)

        val complex3 = TComplex(0.0, -1.0)
        val result3 = complex3.argument()
        val destination3 = -PI / 2.0
        assertEquals(result3, destination3, DELTA)

        val complex4 = TComplex(-1.0, 0.0)
        val result4 = complex4.argument()
        val destination4 = PI
        assertEquals(result4, destination4, DELTA)
    }

    @Test
    fun checkAngle() {
        val complex = TComplex(3.0, 4.0)
        val result = complex.angle()
        val destination = complex.argument() * 180.0 / PI
        assertEquals(result, destination, DELTA)
    }

    @Test
    fun checkPow() {
        val complex = TComplex(2.0, 2.0)
        val result = complex.pow(2)
        val destination = TComplex(0.0, 8.0)
        assertEquals(result.real, destination.real, DELTA)
        assertEquals(result.image, destination.image, DELTA)
    }

    @Test
    fun checkRoot() {
        val complex = TComplex(3.0, 7.0)
        val result = complex.root(3, 1)
        val destination = TComplex(-1.5560058307323912, 1.204044224833373)
        assertEquals(result.real, destination.real, DELTA)
        assertEquals(result.image, destination.image, DELTA)
    }

    @Test
    fun checkEquals() {
        val complex1 = TComplex(2.0,3.0)
        val complex2 = TComplex(2.0,3.0)
        assert(complex1 == complex2)

        val complex3 = TComplex(2.0,-3.0)
        val complex4 = TComplex(2.0,3.0)
        assert(complex3 != complex4)
    }
}