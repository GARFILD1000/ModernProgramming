package com.example.memory

enum class Operation{
    None,
    Add,
    Sub,
    Mul,
    Div
}

enum class Function{
    Rev,
    Sqr
}

class TProc(){
    var lopRes: TData? = null
    var rop: TData? = null
    var operation: Operation = Operation.None

    fun reset(){
        lopRes = null
        rop = null
        operation = Operation.None
    }

    fun clearOperation(){
        operation = Operation.None
    }

    fun runOperation(){
        when(operation){
            Operation.Add -> {
                lopRes?:return
                rop?:return
                lopRes = lopRes!! + rop!!
            }
            Operation.Sub -> {
                lopRes?:return
                rop?:return
                lopRes = lopRes!! - rop!!
            }
            Operation.Mul -> {
                lopRes?:return
                rop?:return
                lopRes = lopRes!! * rop!!
            }
            Operation.Div -> {
                lopRes?:return
                rop?:return
                lopRes = lopRes!! / rop!!
            }
        }
    }

    fun runFunction(function: Function){
        when(function){
            Function.Rev -> {
                rop = rop?.reverse()
            }
            Function.Sqr -> {
                rop = rop?.square()
            }
        }
    }



}