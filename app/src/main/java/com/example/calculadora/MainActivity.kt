package com.example.calculadora

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadora.ui.theme.CalculadoraTheme
import com.example.calculadora.ui.theme.Orange


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraTheme (){
                Calculadora()
            }
        }
    }
}
@Composable
fun Calculadora() {

    var expresion by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ){
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ){
            CustomText(text = expresion, MaterialTheme.colorScheme.background, size = 50f)
            Spacer(modifier = Modifier.size(30.dp))
            CustomText(text = resultado, MaterialTheme.colorScheme.background, size = 70f)

        }

        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("C", modifier = Modifier.weight(2f),
                    onClick = {
                    expresion = ""
                    resultado = ""
                })
                ButtonsCalculadora("⌫", modifier = Modifier.weight(1f),
                    onClick = {
                        expresion = borrarCaracter(expresion)
                    })
                ButtonsCalculadora("÷", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
            }

            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("7", modifier = Modifier.weight(1f),
                    onClick = {
                        expresion += it
                    })
                ButtonsCalculadora("8", modifier = Modifier.weight(1f),
                    onClick = {
                        expresion += it

                    })
                ButtonsCalculadora("9", modifier = Modifier.weight(1f),
                    onClick = {
                        expresion += it

                    })
                ButtonsCalculadora("x", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
            }
            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("4", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora("5", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora("6", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora("-", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
            }


            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("1", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora("2", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora("3", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora("+", modifier = Modifier.weight(1f), onClick = {
                        expresion += it

                })
            }

            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("0", modifier = Modifier.weight(2f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora(".", modifier = Modifier.weight(1f), onClick = {
                    expresion += it

                })
                ButtonsCalculadora("=", modifier = Modifier.weight(1f), onClick = {
                    if (expresion.isNotEmpty()) {
                        resultado = solucionExpresion(expresion)
                    }
                })

            }
        }
    }
}

fun solucionExpresion(expresion: String): String {
    return try {
        val operacion = expresion
            .replace("x", "*")
            .replace("÷", "/")
        val resultado = eval(operacion)
        resultado.toString()

    } catch (e: Exception) {
        "Error ${e}"
    }
}
fun eval(exp: String): Double {

    val tokens = exp.split("(?<=[-+*/])|(?=[-+*/])".toRegex()).filter { it.isNotBlank() }

    var resultado = tokens[0].toDouble()

    for (i in 1 until tokens.size step 2) {
        val operador = tokens[i]
        val siguienteNumero = tokens[i + 1].toDouble()
        when (operador) {
            "+" -> resultado += siguienteNumero
            "-" -> resultado -= siguienteNumero
            "*" -> resultado *= siguienteNumero
            "/" -> if (siguienteNumero != 0.0) resultado /= siguienteNumero else return Double.NaN // Evita división por cero
        }
    }
    return resultado
}
fun borrarCaracter(expresion: String): String {
    return if(expresion.length>=1) {
        expresion.substring(0, expresion.length - 1)
    }else{
        expresion
    }
}
@Composable
fun ButtonsCalculadora(
    buttonSimbolo: String,
    modifier: Modifier = Modifier,
    onClick: (String)-> Unit = {}){

    val colorFondo = when (buttonSimbolo) {
        "⌫", "÷", "x", "-", "+" -> Color.Gray
        "C", "=" -> Orange
        else -> Color.DarkGray
    }

    Button(
        onClick = { onClick(buttonSimbolo) },
        modifier = modifier
            .padding(3.dp)
            .size(80.dp)
            .clip(CircleShape),
        colors = ButtonDefaults.buttonColors(containerColor = colorFondo)){
        CustomText(text = buttonSimbolo, MaterialTheme.colorScheme.background, size = 35f)
    }
}
@Composable
fun CustomText(
    text : String,
    color: Color = Color.Black,
    size : Float= 30f,
    fontWeight: FontWeight = FontWeight.Normal
){
    Text(text, color = color, fontSize = size.sp, fontWeight = fontWeight)
}

@Preview()
@Composable
fun PreviewCalculadora(){
    CalculadoraTheme {
        Calculadora()
    }
}