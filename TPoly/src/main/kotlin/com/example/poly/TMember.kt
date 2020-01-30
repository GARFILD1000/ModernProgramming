package com.example.poly

import java.lang.Math.pow
import kotlin.math.pow

class TMember {
    companion object{
        const val MEMBER = "x"
        val ZERO = TMember(0,0)
    }

    var ratio: Int = 0
    var degree: Int = 0

    constructor(ratio: Int, degree: Int) {
        this.ratio = ratio
        this.degree = degree
    }

    fun calculate(x: Double): Double {
        return x.pow(degree.toDouble()) * ratio
    }

    fun differentiate(): TMember{
        return if (degree > 0) {
            TMember(ratio * degree, degree - 1)
        } else {
            TMember(0, 0)
        }
    }

    override fun equals(other: Any?): Boolean {
        return other is TMember && ratio == other.ratio && degree == other.degree
    }

    override fun toString(): String {
        return when{
            ratio == 1 && degree == 1 -> MEMBER
            degree == 0 -> "$ratio"
            degree == 1 -> "$ratio*$MEMBER"
            ratio == 1 -> "$MEMBER^$degree"
            else -> "$ratio*$MEMBER^$degree"
        }
    }

    operator fun plus(other: TMember): TMember{
        return if (degree == other.degree) {
            TMember(ratio + other.ratio, degree)
        } else {
            TMember(ratio, degree)
        }
    }

    operator fun minus(other: TMember): TMember{
        return if (degree == other.degree) {
            TMember(ratio - other.ratio, degree)
        } else {
            TMember(ratio, degree)
        }
    }

    fun copy() : TMember{
        return TMember(ratio, degree)
    }
}