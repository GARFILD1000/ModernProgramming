package main.com.example.pnumber

import java.lang.Exception
import java.lang.Math.pow
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.math.BigInteger
import kotlin.math.absoluteValue
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class PNumber {
    companion object{
        const val separators = "."
        private val digitsParsing: Map<String, Short> = mapOf<String, Short>(
            "0" to 0,
            "1" to 1,
            "2" to 2,
            "3" to 3,
            "4" to 4,
            "5" to 5,
            "6" to 6,
            "7" to 7,
            "8" to 8,
            "9" to 9,
            "A" to 10,
            "B" to 11,
            "C" to 12,
            "D" to 13,
            "E" to 14,
            "F" to 15)
        private val digitsConverting: Map<Int, String> = mapOf<Int, String>(
            0 to "0",
            1 to "1",
            2 to "2",
            3 to "3",
            4 to "4",
            5 to "5",
            6 to "6",
            7 to "7",
            8 to "8",
            9 to "9",
            10 to "A",
            11 to "B",
            12 to "C",
            13 to "D",
            14 to "E",
            15 to "F")
        private val signParsing: Map<String, Int> = mapOf<String, Int>(
            "+" to 1,
            "-" to -1)
    }

    private var number = 0.0
    var numberBase: NumberBase = NumberBase.TEN
    var precision = 10

    constructor(number: Double, numberBase: NumberBase, precision: Int){
        this.number = number
        this.numberBase = numberBase
        this.precision = precision
    }

    constructor(number: String, numberBase: NumberBase, precision: Int){
        this.number = parseString(number, numberBase, precision)
        this.numberBase = numberBase
        this.precision = precision
    }

    @Throws(WrongNumberException::class)
    fun parseString(number: String, numberBase: NumberBase, precision: Int): Double{
        var newNumber = 0.0

        var numberString = number
        var sign = 1
        number.getOrNull(0)?.let{firstChar ->
            signParsing.get(firstChar.toString())?.let{parsedSign ->
                sign = parsedSign
                numberString = number.removeRange(0, 1)
            }
        }

        val splittedNumber = numberString.replace(",",".").split(separators)
        if (splittedNumber.size > 2){
            throw WrongNumberException("There are more than one decimal separators in number")
        }
        val integerPart = splittedNumber.getOrNull(0)?: ""
        val fractionalPart = splittedNumber.getOrNull(1)?: ""
//            println("splitted $integerPart $fractionalPart")

        var basePowed = 1L
        for (symbol in integerPart.reversed()){
            val isValidDigit = isValidDigit(symbol.toString(), numberBase)
            if (isValidDigit){
                val digit = digitsParsing.get(symbol.toString())
                val additional = basePowed * digit!!
//                println("$basePowed * $digit = $additional")
                newNumber += additional
//                println("number = $newNumber")
            }
            else{
                throw WrongNumberException("There is wrong digit '$symbol' in number with the given base")
            }
            basePowed *= numberBase.base
        }
        basePowed = numberBase.base.toLong()
        var counter = 0
        for (symbol in fractionalPart){
            counter++
            if (counter > precision) break
            val isValidDigit = isValidDigit(symbol.toString(), numberBase)
            if (isValidDigit){
                val digit = digitsParsing.get(symbol.toString())
                val additional = (digit!!.toDouble() / basePowed.toDouble())
//                println("$basePowed * $digit = $additional")
                if (additional.isNaN()) break
                newNumber += additional
//                println("number = $newNumber")
            }
            else{
                throw WrongNumberException("There is wrong digit '$symbol' in number with the given base")
            }
            basePowed *= numberBase.base
        }
        newNumber *= sign
        return newNumber
    }

    private fun isValidDigit(digit: String, numberBase: NumberBase): Boolean{
        val parsed = digitsParsing.get(digit)
        return parsed != null && parsed < numberBase.base
    }

    override fun toString(): String{
        return number.toString()
    }

    fun toString(base: NumberBase): String{
        var result = StringBuilder()
//        println("number = $number")
        var remains = 0
        var quotient = number.toInt().absoluteValue
        while(quotient >= base.base){
//            println("$quotient and remains $remains")
            remains = quotient % base.base
            quotient = quotient / base.base
            result.append(digitsConverting.get(remains))
        }
//        println("$quotient and remains $remains")
        result.append(digitsConverting.get(quotient))
        if (number < 0) result.append("-")
        result.reverse()

        var fractional = (number - (number.toInt())).absoluteValue
//        println("Fractional part = $fractional")
        if (fractional > 0.0) {
            result.append(separators)
            for (i in 0 until precision) {
                fractional = fractional * base.base
                result.append(digitsConverting.get(fractional.toInt()))
                fractional = (fractional - (fractional.toInt()))
            }
        }
        return result.toString()
    }

    fun getNumber(): Double = number

    operator fun plus(otherNumber: PNumber): PNumber{
        return PNumber(this.number + otherNumber.number, this.numberBase, this.precision)
    }

    operator fun minus(otherNumber: PNumber): PNumber{
        return PNumber(this.number - otherNumber.number, this.numberBase, this.precision)
    }

    operator fun times(otherNumber: PNumber): PNumber{
        return PNumber(this.number * otherNumber.number, this.numberBase, this.precision)
    }

    operator fun div(otherNumber: PNumber): PNumber{
        return PNumber(this.number / otherNumber.number, this.numberBase, this.precision)
    }

    fun reverse(): PNumber{
        return PNumber(1.0 / this.number, this.numberBase, this.precision)
    }

    fun square(): PNumber{
        return PNumber(this.number * this.number, this.numberBase, this.precision)
    }
}

fun main(args: Array<String>) {
    //val number = PNumber("-1010.0001",NumberBase.TWO,10)
    val number = PNumber("-A.1",NumberBase.SIXTEEN,10)
    //val number = PNumber("-10.0625",NumberBase.TEN,10)

    println("In dec $number")
    println("In binary ${number.toString(NumberBase.TWO)}")
    println("In hex ${number.toString(NumberBase.SIXTEEN)}")


    println("\n")
    var number2 = PNumber("1.01",NumberBase.TEN,10)
    var number3 = PNumber("10.01",NumberBase.TEN,10)
    number2 += number3

    println("In dec ${number2.toString(NumberBase.TEN)}")
    println("In binary ${number2.toString(NumberBase.TWO)}")
    println("In hex ${number2.toString(NumberBase.SIXTEEN)}")
}

class WrongNumberException(): Exception(){
    var description: String = ""
    constructor(description: String): this(){
        this.description = description
    }
    override val message: String?
        get() = "Wrong Number: $description"
}

enum class NumberBase(val base: Int){
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIFE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(11),
    TWELVE(12),
    THIRTEEN(13),
    FOURTEEN(14),
    FIFTEEN(15),
    SIXTEEN(16)
}