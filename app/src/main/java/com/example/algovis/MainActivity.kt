package com.example.algovis

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.primarySurface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.algovis.algoritms.InsertionSort
import com.example.algovis.components.VisBottomBar
import com.example.algovis.components.VisualizerSection
import com.example.algovis.ui.theme.AlgoVisTheme
import com.example.algovis.components.AlgorithmSelectionDropdown


class MainActivity : ComponentActivity() {

    enum class Algorithm{
        InsertionSort,
        BubbleSort,
        MergeSort
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AlgoVisTheme {
                var selectedAlgorithm by remember { mutableStateOf(Algorithm.InsertionSort) }

                val viewModel: AlgorithmViewModel by lazy {
                    val viewModelProviderFactory = AlgorithmViewModelProvider(selectedAlgorithm)
                    ViewModelProvider(
                        this,
                        viewModelProviderFactory
                    )[AlgorithmViewModel::class.java]
                }
                Box(
                    contentAlignment = Alignment.BottomCenter,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFff8500))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        AlgorithmSelectionDropdown(selectedAlgorithm) {
                            selectedAlgorithm = it
                            viewModel.changeAlgorithm(it)
                        }

                        // Add a spacer to push the rest of the content to the bottom
                        Spacer(modifier = Modifier.weight(1f))

                        VisualizerSection(
                            arr = viewModel.arr.value,
                            modifier = Modifier.fillMaxWidth()
                        )

                        val isPlaying = viewModel.isPlaying.value
                        val isFinished = viewModel.onSortingFinish.value

                        VisBottomBar(
                            playPauseClick = { viewModel.onEvent(AlgorithmEvents.PlayPause) },
                            slowDownClick = { viewModel.onEvent(AlgorithmEvents.SlowDown) },
                            speedUpClick = { viewModel.onEvent(AlgorithmEvents.SpeedUp) },
                            previousClick = { viewModel.onEvent(AlgorithmEvents.Previous) },
                            nextClick = { viewModel.onEvent(AlgorithmEvents.Next) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(75.dp),
                            isPlaying = if (isFinished) !isFinished else isPlaying
                        )
                    }}
                }
            }
            }
        }

