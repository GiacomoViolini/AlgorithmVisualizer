package com.example.algovis

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.algovis.algoritms.AlgorithmBP
import com.example.algovis.algoritms.Converter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlgorithmViewModel(
    private val algorithm: MainActivity.Algorithm
): ViewModel(){
    private var selectedAlgorithm = Converter.algorithmImplementations[algorithm]
    var arr = mutableStateOf(
        intArrayOf(
            1, 197, 290, 113, 399, 437, 303, 336, 195, 426, 347, 564, 474, 373, 62, 78, 135, 322, 452, 89,
            275, 471, 21, 493, 381, 496, 542, 385, 481, 531, 68, 108, 294, 71, 209, 438, 260, 104, 493, 415,
            181, 392, 71, 6, 424, 569, 377, 186, 300, 270, 508, 27, 439, 50, 121, 472, 183, 66, 171, 179,
            130, 515, 128, 90, 208, 287, 123, 258, 262, 431,
        )
    )
    val copy = arr.value.clone()

    val isPlaying = mutableStateOf(false)
    val onSortingFinish = mutableStateOf(false)
    private var delay = 50L
    private var pause = false

    private var next = 1
    private var previous = 0

    private var sortedArrayLevels = mutableListOf<List<Int>>()

    var coroutineJob by mutableStateOf<Job?>(null)

    init{
        coroutineJob = viewModelScope.launch {
            selectedAlgorithm!!.sort(
                arr.value.clone()
            ){
                modifiedArray -> sortedArrayLevels.add(modifiedArray.toMutableList())
            }
        }
    }

    fun changeAlgorithm(newAlgorithm: MainActivity.Algorithm) {
        selectedAlgorithm = Converter.algorithmImplementations[newAlgorithm]
        restartSortingProcess()
        // You can perform additional actions if needed
    }

    private fun restartSortingProcess() {
        sortedArrayLevels.clear()
        coroutineJob?.cancel()
        arr.value = copy
        sortingState = 0
        pause()
        coroutineJob = viewModelScope.launch {
            selectedAlgorithm!!.sort(
                arr.value.clone()
            ) { modifiedArray ->
                sortedArrayLevels.add(modifiedArray.toMutableList())
            }
        }
    }

    fun onEvent( event : AlgorithmEvents){
        when(event){
            is AlgorithmEvents.PlayPause ->{
                playPauseAlgorithm ()
            }
            is AlgorithmEvents.SlowDown ->{
                slowDown ()
            }
            is AlgorithmEvents.SpeedUp ->{
                speedUp ()
            }
            is AlgorithmEvents.Previous ->{
                previous ()
            }
            is AlgorithmEvents.Next ->{
                next ()
            }
        }
    }

    private fun next() {
        if(next < sortedArrayLevels.size){
            arr.value = sortedArrayLevels[next].toIntArray()
            next++
            previous++
        }
    }

    private fun previous() {
        if(previous >= 0){
            arr.value = sortedArrayLevels[previous].toIntArray()
            next--
            previous--
        }
    }

    private fun speedUp() {
        if(delay >= 50)
            delay -= 40
    }

    private fun slowDown() {
        if(delay <= 50)
            delay += 40
    }

    private fun playPauseAlgorithm() {
        if(onSortingFinish.value){
            sortingState = 0
            onSortingFinish.value = false
            arr.value = copy
            return
        }
        if(isPlaying.value){
            pause()
        }
        else{
            play()
        }
        isPlaying.value = !isPlaying.value
    }

    private var sortingState = 0
    private fun play() = viewModelScope.launch{
        pause = false
        for( i in sortingState until sortedArrayLevels.size){
            if(!pause){
                delay(delay)
                arr.value = sortedArrayLevels[i].toIntArray()
            }
            else {
                sortingState = i
                next = i+1
                previous = i
                return@launch
            }
        }
        onSortingFinish.value = true
        isPlaying.value = false
    }

    private fun pause() {
        pause = true
    }
}