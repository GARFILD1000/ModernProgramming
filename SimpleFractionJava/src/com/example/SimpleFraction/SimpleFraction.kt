package com.example.SimpleFraction

import java.lang.Exception
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.roundToLong

class SimpleFraction {
    companion object{
        var DELTA = 0.00000001

        @JvmStatic
        fun main(args: Array<String>) {
            println(SimpleFraction("1/3") - SimpleFraction("2/4"))
        }
    }

    var numerator: Long = 0L
    var denominator: Long = 0L

    constructor(numerator: Long, denominator: Long) {
        init(numerator, denominator)
        reduceFraction()
    }

    constructor(fractionString: String) {
        val parts = fractionString.split("/")
        var newNumerator = 0L
        var newDenominator = 1L
        try {
            when (parts.size) {
                2 -> {
                    newNumerator = parts[0].toLongOrNull() ?: parts[0].toDouble().roundToLong()
                    newDenominator = parts[1].toLongOrNull() ?: parts[1].toDouble().roundToLong()
                }
                1 -> {
                    newNumerator = parts[0].toLongOrNull() ?: parts[0].toDouble().roundToLong()
                }
            }
        }
        catch(ex: Exception){
            throw WrongNumberException(ex.toString())
        }
        finally {
            init(newNumerator, newDenominator)
        }
    }

    constructor(oldFraction: SimpleFraction){
        init(oldFraction.numerator, oldFraction.denominator)
    }

    private fun init(numerator: Long, denominator: Long) {
        this.numerator = numerator
        this.denominator = denominator
    }

    fun copy(): SimpleFraction{
        return SimpleFraction(this.numerator, this.denominator)
    }

    fun reduceFraction(){
        val gcd: Long = getGreatestCommonDivisor(numerator, denominator)
        if (gcd != 0L){
            numerator = numerator / gcd
            denominator = denominator / gcd
            if (numerator < 0 && denominator < 0){
                numerator = numerator.absoluteValue
                denominator = denominator.absoluteValue
            }
            else if (denominator < 0){
                numerator = -numerator
                denominator = denominator.absoluteValue
            }
        }
    }

    private fun setCommonDenominatorWith(fraction: SimpleFraction){
        if (this.denominator != fraction.denominator){
            val lcd = getLowestCommonDenominator(this.denominator, fraction.denominator)
            this.numerator = this.numerator * (lcd / this.denominator)
            fraction.numerator = fraction.numerator * (lcd / fraction.denominator)
            this.denominator = lcd
            fraction.denominator = lcd
        }
    }

    private fun getLowestCommonDenominator(denominator1: Long, denominator2: Long): Long{
        return denominator1 * denominator2 / getGreatestCommonDivisor(denominator1, denominator2)
    }

    private fun getGreatestCommonDivisor(number1: Long, number2: Long): Long{
        var tempNumber1 = number1.absoluteValue
        var tempNumber2 = number2.absoluteValue
        if (tempNumber1 == 0L || tempNumber2 == 0L){
            return 0L
        }
        if (tempNumber1 == 1L || tempNumber2 == 1L){
            return 1L
        }
        while (tempNumber1 != tempNumber2){
            if (tempNumber1 > tempNumber2){
                tempNumber1 -= tempNumber2
            }
            else{
                tempNumber2 -= tempNumber1
            }
        }
        return tempNumber1
    }

    fun getReversed(): SimpleFraction{
        return SimpleFraction(denominator, numerator)
    }

    fun getNegative(): SimpleFraction{
        return SimpleFraction(numerator, denominator).apply{
            setNegative()
        }
    }

    fun setNegative(){
        numerator = -numerator
    }

    fun getAbs(): SimpleFraction{
        return SimpleFraction(numerator.absoluteValue, denominator.absoluteValue)
    }

    fun getSquare(): SimpleFraction{
        return SimpleFraction(numerator*numerator, denominator*denominator)
    }

    operator fun plus(operator2: SimpleFraction): SimpleFraction{
        var result = SimpleFraction(0,1)
        this.reduceFraction()
        operator2.reduceFraction()
        setCommonDenominatorWith(operator2)
        result.numerator = numerator + operator2.numerator
        result.denominator = denominator
        result.reduceFraction()
        reduceFraction()
        operator2.reduceFraction()
        return result
    }

    operator fun minus(operator2: SimpleFraction): SimpleFraction{
        var result = SimpleFraction(0,0)
        this.reduceFraction()
        operator2.reduceFraction()
        setCommonDenominatorWith(operator2)
        result.numerator = numerator - operator2.numerator
        result.denominator = denominator
        result.reduceFraction()
        reduceFraction()
        operator2.reduceFraction()
        return result
    }

    operator fun times(operator2: SimpleFraction): SimpleFraction{
        var result = SimpleFraction(0,0)
        this.reduceFraction()
        operator2.reduceFraction()
        result.numerator = numerator * operator2.numerator
        result.denominator = denominator * operator2.denominator
        result.reduceFraction()
        reduceFraction()
        operator2.reduceFraction()
        return result
    }

    operator fun div(operator2: SimpleFraction): SimpleFraction{
        var result = SimpleFraction(0,0)
        this.reduceFraction()
        operator2.reduceFraction()
        setCommonDenominatorWith(operator2)
        result.numerator = numerator * operator2.denominator
        result.denominator = denominator * operator2.numerator
        result.reduceFraction()
        reduceFraction()
        operator2.reduceFraction()
        return result
    }

    override fun equals(other: Any?): Boolean {
        return (other is SimpleFraction) && other.numerator == numerator && other.denominator == denominator
    }

    operator fun compareTo(fraction: SimpleFraction): Int{
        var result = 0
        this.reduceFraction()
        fraction.reduceFraction()
        if (this.denominator == fraction.denominator){
            result = if (this.numerator == fraction.numerator) 0
            else if (this.numerator > fraction.numerator) 1
            else -1
        }
        else {
            val fraction1 = this.toDouble()
            val fraction2 = fraction.toDouble()
            result = if (abs(fraction1-fraction2) < DELTA) 0
            else if (fraction1 > fraction2) 1
            else -1
        }
        return result
    }

    fun getNumeratorString(): String{
        return numerator.toString()
    }

    fun getDenominatorString(): String{
        return denominator.toString()
    }

    override fun toString(): String{
        return if (numerator == 0L){
            "0"
        }
        else if (denominator == 1L){
            "$numerator"
        }
        else {
            "$numerator / $denominator"
        }
    }

    fun toDouble(): Double{
        return numerator.toDouble() / denominator
    }

    fun getInteger(): Long{
        return (numerator.toDouble() / denominator).roundToLong()
    }

//    private fun checkFractionFloat(parts: List<String>): Boolean{
//        var isFloat = false
//        for (part in parts){
//            if (part.contains(",") || part.contains(".")){
//                isFloat = true
//                break
//            }
//        }
//        return isFloat
//    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    class WrongNumberException(val description: String): Exception() {
        override fun toString(): String {
            return super.toString() + " : " + description
        }
    }
}