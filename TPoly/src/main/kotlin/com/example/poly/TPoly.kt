package com.example.poly

import java.util.*

class TPoly {
    private var polynomial: LinkedList<TMember> = LinkedList()

    constructor(){
        //polynomial.add(TMember(0,0))
    }

    constructor(ratio: Int, degree: Int){
        polynomial.add(TMember(ratio, degree))
    }

    fun getMaxDegree(): Int{
        var maxDegree = 0
        polynomial.forEach {
            if (it.degree > maxDegree) maxDegree = it.degree
        }
        return maxDegree
    }

    fun getRatio(degree: Int): Int {
        for (member in polynomial) {
            if (member.degree == degree) {
                return member.ratio
            }
        }
        return 0
    }

    fun clear() {
        polynomial.clear()
//        polynomial.add(TMember(0,0))
    }

    fun getElement(idx: Int): TMember? {
        return polynomial.get(idx)
    }

    private fun reduce(){
        val newElements = LinkedList<TMember>()
        val degrees = getDifferentDegrees().sortedDescending()
        for (degree in degrees) {
            val elements = getElementsWithDegree(degree)
            var result = TMember(0, degree)
            elements.forEach {
                result += it
            }
            if (result != TMember.ZERO) {
                newElements.addLast(result)
            }
        }
        polynomial = newElements
    }

    private fun getElementsWithDegree(degree: Int): List<TMember>{
        val result = LinkedList<TMember>()
        for (element in polynomial) {
            if (element.degree == degree) result.addLast(element)
        }
        return result
    }

    private fun getDifferentDegrees(): List<Int>{
        val degrees = LinkedList<Int>()
        for (element in polynomial) {
            val degree = element.degree
            if (!degrees.contains(degree)) degrees.addLast(degree)
        }
        return degrees
    }

    operator fun plus(other: TPoly): TPoly{
        val result = TPoly()
        result.polynomial.addAll(polynomial)
        result.polynomial.addAll(other.polynomial)
        result.reduce()
        return result
    }

    operator fun plus(member: TMember): TPoly{
        val result = TPoly()
        result.polynomial.addAll(polynomial)
        result.polynomial.add(member)
        result.reduce()
        return result
    }

    operator fun times(other: TPoly): TPoly{
        val result = TPoly()
        result.polynomial.clear()
        for (element in polynomial) {
            for (secondElement in other.polynomial) {
                result.polynomial.add(TMember(
                    element.ratio*secondElement.ratio,
                    element.degree+secondElement.degree
                ))
            }
        }
        result.reduce()
        return result
    }

    operator fun minus(other: TPoly): TPoly {
        val result = TPoly()
        result.polynomial.addAll(polynomial)
        for (element in other.polynomial) {
            val newMember = element.copy().apply {
                this.ratio = -this.ratio
            }
            result.polynomial.add(newMember)
        }
        result.reduce()
        return result
    }

    fun differentiate(): TPoly {
        val result = TPoly()
        for (element in polynomial) {
            result.polynomial.add(element.differentiate())
        }
        result.reduce()
        return result
    }

    fun calculate(x: Double): Double {
        var result = 0.0
        for (element in polynomial) {
            result += element.calculate(x)
        }
        return result
    }

    override fun toString(): String {
        return if (polynomial.isNotEmpty()) {
            polynomial.joinToString(" + ")
        } else {
            "0"
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other !is TPoly) return false
        if (polynomial.size != other.polynomial.size) return false
        var equals = true
        for (element in polynomial) {
            if (!other.polynomial.contains(element)) {
                equals = false
                break
            }
        }
        if (equals) {
            for (element in other.polynomial) {
                if (!polynomial.contains(element)) {
                    equals = false
                    break
                }
            }
        }
        return equals
    }
}

fun main(){
//    val poly1 = TPoly(3, 2) + TMember(-2, 1) + TMember(7, 0)
//    val poly2 = TPoly(5, 2) + TMember(4, 1) + TMember(-3, 0)
//    println(poly1)
//    println(poly2)
//    val result = poly1 * poly2
//    print(result)

    val poly = TPoly(1,3) + TMember(7,2) + TMember(5,1) + TMember(2,0)
    val result = poly.differentiate()
    println(result)
}