package com.example.pnumber

class TPNumberEditor {
    companion object {
        const val ZERO = "0"
        const val SIGN = "-"
        const val DIVIDER = "."
    }

    var number: String = ZERO

    fun isZero(): Boolean = number == ZERO

    fun changeSign(): TPNumberEditor {
        if (number.contains(SIGN)) {
            number.replace(SIGN, "")
        } else {
            number = SIGN + number
        }
        return this
    }

    fun addDigit(digit: Int): TPNumberEditor {
        if (digit in 0..9) {
            if (number == SIGN + ZERO) {
                number = SIGN
            } else if (number == ZERO) {
                number = ""
            }
            number += digit.toString()
        }
        return this
    }

    fun addZero(): TPNumberEditor {
        return addDigit(0)
    }

    fun removeLastDigit(): TPNumberEditor {
        val lastDigitIdx = number.length - 1
        if (lastDigitIdx > 0) {
            number = number.substring(0, lastDigitIdx)
        } else if (lastDigitIdx == 0){
            number = ""
        }
        if (number == SIGN || number.isEmpty()) {
            addZero()
        }
        return this
    }

    fun addDivider(): TPNumberEditor {
        if (!number.contains(DIVIDER)){
            number += DIVIDER
        }
        return this
    }

    fun editNumber(commandNumber: Int, value: Int = 0): TPNumberEditor {
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

    fun clear(): TPNumberEditor {
        number = ZERO
        return this
    }
}
