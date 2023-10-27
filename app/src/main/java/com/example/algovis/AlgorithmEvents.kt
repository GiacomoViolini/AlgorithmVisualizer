package com.example.algovis

sealed class AlgorithmEvents{
    object SlowDown : AlgorithmEvents ()
    object PlayPause : AlgorithmEvents ()
    object SpeedUp : AlgorithmEvents ()
    object Previous : AlgorithmEvents ()
    object Next : AlgorithmEvents ()
}
