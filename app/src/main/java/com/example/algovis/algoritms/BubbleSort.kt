package com.example.algovis.algoritms

class BubbleSort : AlgorithmBP {
    override suspend fun sort(
        arr: IntArray, onSwap: (IntArray) -> Unit
    ) {
        for (i in 0 until arr.size) {
            for (j in i + 1 until arr.size) {
                if (arr[i] > arr[j]) {
                    var temp = arr[i]
                    arr[i] = arr[j]
                    arr[j] = temp
                    onSwap(arr)
                }
            }
        }
    }
}