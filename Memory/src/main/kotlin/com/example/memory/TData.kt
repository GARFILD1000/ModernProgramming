package com.example.memory

abstract class TData{
    abstract operator fun plus(other: TData): TData
    abstract operator fun minus(other: TData): TData
    abstract operator fun times(other: TData): TData
    abstract operator fun div(other: TData): TData
    abstract fun reverse(): TData
    abstract fun square(): TData
}