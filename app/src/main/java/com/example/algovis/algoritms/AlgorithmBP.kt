package com.example.algovis.algoritms

interface AlgorithmBP {
    suspend fun sort( arr: IntArray, onSwap : (IntArray)-> Unit)
}