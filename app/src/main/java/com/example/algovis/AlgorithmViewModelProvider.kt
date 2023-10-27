package com.example.algovis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AlgorithmViewModelProvider (
    private var selectedAlgorithm: MainActivity.Algorithm
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlgorithmViewModel(selectedAlgorithm) as T
    }
}