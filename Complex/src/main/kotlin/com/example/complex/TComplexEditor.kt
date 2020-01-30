package com.example.complex

class TComplexEditor {
    companion object {
        const val ZERO = "0"
        const val SIGN = "-"
        const val FRACTION_DIVIDER = "."
        const val DIVIDER = " + "
        const val IMAGE = "i"
    }

    var complex: String = ZERO
    private set
    get(){
        if (real == SIGN) real += ZERO
        if (image == SIGN) image += ZERO
        var string = if (real.isEmpty()) ZERO else real
        if (dividerEnabled){
            string += DIVIDER
            string += if (image.isEmpty()) ZERO else image
            string += IMAGE
        }
        return string
    }
    private var real: String = ""
    private var image: String = ""
    private var dividerEnabled = false

    fun isZero(): Boolean = complex == ZERO

    private fun getCurrent(): String = if (!dividerEnabled) real else image

    private fun setCurrent(value: String) {
        if (!dividerEnabled) real = value
        else image = value
    }

    fun changeSign(): TComplexEditor {
        var current = getCurrent()
        if (current.contains(SIGN)) {
            current.replace(SIGN, "")
        } else {
            current = SIGN + current
        }
        setCurrent(current)
        return this
    }

    fun addDigit(digit: Int): TComplexEditor {
        if (digit in 0..9) {
            var current = getCurrent()
            if (current == SIGN + ZERO) {
                current = SIGN
            } else if (complex == ZERO) {
                current = ""
            }
            current += digit.toString()
            setCurrent(current)
        }
        return this
    }

    fun addZero(): TComplexEditor {
        return addDigit(0)
    }

    fun removeLastDigit(): TComplexEditor {
        var current = getCurrent()
        if (current.isEmpty() && dividerEnabled) {
            dividerEnabled = false
            return this
        }
        val lastDigitIdx = current.length - 1
        if (lastDigitIdx > 0) {
            current = current.substring(0, lastDigitIdx)
        } else if (lastDigitIdx == 0){
            current = ""
        }
        setCurrent(current)
        return this
    }

    fun addDivider(): TComplexEditor {
        dividerEnabled = true
        return this
    }

    fun addDot(): TComplexEditor {
        var current = getCurrent()
        if (!current.contains(FRACTION_DIVIDER)) {
            current += FRACTION_DIVIDER
        }
        setCurrent(current)
        return this
    }

    fun editComplex(commandNumber: Int, value: Int = 0): TComplexEditor{
        when(commandNumber){
            0 -> changeSign()
            1 -> addZero()
            2 -> addDigit(value)
            3 -> removeLastDigit()
            4 -> clear()
            5 -> addDivider()
        }
        return this
    }

    fun clear(): TComplexEditor {
        real = ""
        image = ""
        dividerEnabled = false
        return this
    }
}