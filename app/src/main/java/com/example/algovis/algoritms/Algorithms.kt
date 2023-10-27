package com.example.algovis.algoritms

import com.example.algovis.MainActivity

object Converter {
    val algorithmImplementations = mapOf(
        MainActivity.Algorithm.InsertionSort to InsertionSort(),
        MainActivity.Algorithm.BubbleSort to BubbleSort(),
        MainActivity.Algorithm.MergeSort to MergeSort(),
    )
}