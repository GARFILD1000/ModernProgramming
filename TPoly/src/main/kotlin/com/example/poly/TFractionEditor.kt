package com.example.poly

class TFractionEditor {
    companion object {
        const val ZERO = "0"
        const val SIGN = "-"
        const val DIVIDER = "/"
    }

    var fraction: String = ZERO

    fun isZero(): Boolean = fraction == ZERO

    fun changeSign(): TFractionEditor {
        if (fraction.contains(SIGN)) {
            fraction.replace(SIGN, "")
        } else {
            fraction = SIGN + fraction
        }
        return this
    }

    fun addDigit(digit: Int): TFractionEditor {
        if (digit in 0..9) {
            if (fraction == SIGN + ZERO) {
                fraction = SIGN
            } else if (fraction == ZERO) {
                fraction = ""
            }
            if (fraction.isEmpty() || !(digit == 0 && fraction.last().toString() == DIVIDER)) {
                fraction += digit.toString()
            }
        }
        return this
    }

    fun addZero(): TFractionEditor {
        return addDigit(0)
    }

    fun removeLastDigit(): TFractionEditor {
        val lastDigitIdx = fraction.length - 1
        if (lastDigitIdx > 0) {
            fraction = fraction.substring(0, lastDigitIdx)
        } else if (lastDigitIdx == 0){
            fraction = ""
        }
        if (fraction == SIGN || fraction.isEmpty()) {
            addZero()
        }
        return this
    }

    fun addDivider(): TFractionEditor {
        if (!fraction.contains(DIVIDER)){
            fraction += DIVIDER
        }
        return this
    }

    fun editFraction(commandNumber: Int, value: Int = 0): TFractionEditor{
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

    fun clear(): TFractionEditor {
        fraction = ZERO
        return this
    }
}