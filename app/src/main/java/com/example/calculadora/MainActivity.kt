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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            CustomText(text = "0", MaterialTheme.colorScheme.background, size = 60f)
        }

        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("C", modifier = Modifier.weight(2f))
                ButtonsCalculadora("⌫", modifier = Modifier.weight(1f))
                ButtonsCalculadora("÷", modifier = Modifier.weight(1f))
            }

            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("7", modifier = Modifier.weight(1f))
                ButtonsCalculadora("8", modifier = Modifier.weight(1f))
                ButtonsCalculadora("9", modifier = Modifier.weight(1f))
                ButtonsCalculadora("x", modifier = Modifier.weight(1f))
            }
            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("4", modifier = Modifier.weight(1f))
                ButtonsCalculadora("5", modifier = Modifier.weight(1f))
                ButtonsCalculadora("6", modifier = Modifier.weight(1f))
                ButtonsCalculadora("-", modifier = Modifier.weight(1f))
            }


            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("1", modifier = Modifier.weight(1f))
                ButtonsCalculadora("2", modifier = Modifier.weight(1f))
                ButtonsCalculadora("3", modifier = Modifier.weight(1f))
                ButtonsCalculadora("+", modifier = Modifier.weight(1f))
            }

            Row(modifier = Modifier.fillMaxWidth()){
                ButtonsCalculadora("0", modifier = Modifier.weight(2f))
                ButtonsCalculadora(".", modifier = Modifier.weight(1f))
                ButtonsCalculadora("=", modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ButtonsCalculadora(
    buttonSimbolo: String,
    modifier: Modifier = Modifier){

    val colorFondo = when (buttonSimbolo) {
        "⌫", "÷", "x", "-", "+" -> Color.Gray
        "C", "=" -> Orange
        else -> Color.DarkGray
    }

    Button(

        onClick = {},
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
