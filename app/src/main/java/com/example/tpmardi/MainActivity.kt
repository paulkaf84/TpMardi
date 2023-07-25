package com.example.tpmardi



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PGCDApp()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)


@Composable
fun PGCDApp() {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf<Triple<Int, Int, Int>?>(null) }
    val context = LocalContext.current
//    Spacer(modifier = Modifier.width(62.dp))
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
    
    ){
        Text(text = "COEFFISCIENT DE BEZOUT")
        OutlinedTextField(
            value = number1,
            onValueChange = { number1 = it },
            label = { Text("Nombre 1") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        OutlinedTextField(
            value = number2,
            onValueChange = { number2 = it },
            label = { Text("Nombre 2") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Button(
            onClick = {
                val a = number1.toIntOrNull() ?: 0
                val b = number2.toIntOrNull() ?: 0
                result = calculerPGCDetCoefficients(a, b)
            },
            content = { Text("Calculer") },
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        result?.let { (pgcd, u, v) ->
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("PGCD: ")
                    }
                    append(pgcd.toString())
                    append("\n")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Coefficients de Bézout (u et v): ")
                    }
                    append("$u et $v")
                },fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(0.8f)
            )
        }
    }
}

fun calculerPGCDetCoefficients(a: Int, b: Int): Triple<Int, Int, Int> {
// L'algorithme d'Euclide étendu pour trouver le PGCD et les coefficients de Bézout.
    var r0 = a
    var r1 = b
    var u0 = 1
    var v0 = 0
    var u1 = 0
    var v1 = 1
    while (r1 != 0) {
        val q = r0 / r1
        val tempR = r1
        r1 = r0 - q * r1
        r0 = tempR
        val tempU = u1
        u1 = u0 - q * u1
        u0 = tempU
        val tempV = v1
        v1 = v0 - q * v1
        v0 = tempV
    }
    return Triple(r0, u0, v0)
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PGCDApp()
}
