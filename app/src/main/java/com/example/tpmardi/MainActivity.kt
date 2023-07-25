package com.example.tpmardi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpmardi.ui.theme.TpMardiTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TpMardiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BMIApp()
                }
            }
        }
    }
}

@Composable
fun BMIApp() {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
        ) {
            Text(text = "BMI Calculator", modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 50.dp, bottom = 16.dp))
        }
        Spacer(modifier = Modifier.height(60.dp))
        BMICalculator()
    }

}

fun getBMIDescription(bmi: Float): String {
    return when {
        bmi <= 18.5 -> "underweight"
        bmi > 18.5 && bmi <= 24.9 -> "healthy weight"
        bmi > 25.0 && bmi <= 29.9 -> "overweight"
        bmi > 30.0 && bmi <= 34.9 -> "Class I obesity"
        bmi > 35.0 && bmi <= 39.9 -> "Class II obesity"
        else -> "class III obesity"
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculator() {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = weight,
            onValueChange = {
                weight = it
                bmi = (weight.toFloatOrNull() ?: 0f) / (height.toFloatOrNull()?.pow(2) ?: 1f)
            },
            label = {
                Text(text = "Your Weight")
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = height,
            onValueChange = {
                height = it
                bmi = (weight.toFloatOrNull() ?: 0f) / (height.toFloatOrNull()?.pow(2) ?: 1f)
            },
            label = {
                Text(text = "Your height")
            },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.LightGray),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

//        Spacer(modifier = Modifier.height(24.dp))
/*
        Button(onClick = {
            bmi = weight.toFloat() / height.toFloat().pow(2)
        },
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            Text(text = "Calculate", modifier = Modifier.padding(vertical = 8.dp))
        }*/

        Spacer(modifier = Modifier.height(24.dp))
        Text(text = "You are ${getBMIDescription(bmi)} \nYour BMI is: $bmi")
    }
}

@Preview
@Composable
fun BMICalculatorPreview() {
    BMICalculator()
}