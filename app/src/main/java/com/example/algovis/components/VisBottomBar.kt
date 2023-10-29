package com.example.algovis.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.algovis.R

@Composable
fun VisBottomBar(
    modifier: Modifier = Modifier,
    playPauseClick : () -> Unit,
    slowDownClick : () -> Unit,
    speedUpClick : () -> Unit,
    previousClick : () -> Unit,
    nextClick : () -> Unit,
    isPlaying : Boolean = false
){
    BottomAppBar (
        modifier = modifier,
        backgroundColor = Color(0xfff6aa1c)
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ){
            IconButton(
                onClick = slowDownClick
            )
            {
                Icon(
                    painter = painterResource(id = R.drawable.ic_horizontal),
                    contentDescription = "Slow Down",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = playPauseClick
            )
            {
                Icon(
                    painter = painterResource(id = if (!isPlaying) R.drawable.ic_play else R.drawable.ic_pause),
                    contentDescription = "PlayPause",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = speedUpClick
            )
            {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Speed Up",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = previousClick
            )
            {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Previous",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = nextClick
            )
            {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = Color.White
                )
            }
        }
    }
}