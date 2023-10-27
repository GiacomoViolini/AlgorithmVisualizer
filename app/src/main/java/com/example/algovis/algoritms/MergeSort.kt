package com.example.algovis.algoritms

class MergeSort : AlgorithmBP {
    override suspend fun sort(arr: IntArray, onSwap: (IntArray) -> Unit) {
        mergeSort(arr, 0, arr.size - 1, onSwap)
    }

    private suspend fun mergeSort(arr: IntArray, left: Int, right: Int, onSwap: (IntArray) -> Unit) {
        if (left < right) {
            val middle = left + (right - left) / 2
            mergeSort(arr, left, middle, onSwap)
            mergeSort(arr, middle + 1, right, onSwap)
            merge(arr, left, middle, right, onSwap)
        }
    }

    private suspend fun merge(arr: IntArray, left: Int, middle: Int, right: Int, onSwap: (IntArray) -> Unit) {
        val n1 = middle - left + 1
        val n2 = right - middle

        val leftArray = IntArray(n1)
        val rightArray = IntArray(n2)

        for (i in 0 until n1) {
            leftArray[i] = arr[left + i]
        }
        for (i in 0 until n2) {
            rightArray[i] = arr[middle + 1 + i]
        }

        var i = 0
        var j = 0
        var k = left

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i]
                i++
            } else {
                arr[k] = rightArray[j]
                j++
            }
            onSwap(arr.copyOf()) // Notify the UI of the swap
            k++
        }

        while (i < n1) {
            arr[k] = leftArray[i]
            i++
            k++
            onSwap(arr.copyOf()) // Notify the UI of the swap
        }

        while (j < n2) {
            arr[k] = rightArray[j]
            j++
            k++
            onSwap(arr.copyOf()) // Notify the UI of the swap
        }
    }
}