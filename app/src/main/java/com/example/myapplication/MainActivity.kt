package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { GaleriaLayout() }
            }
        }
    }
}

data class pecaArtistica(
    val indexImagem: Int,
    val titulo: String,
    val autor: String,
    val ano: String
)

@Composable
fun GaleriaLayout() {

    val pecas = listOf(
        pecaArtistica(R.drawable.obra1, "Samba", "Di Cavalcanti", "1925"),
        pecaArtistica(R.drawable.obra2, "Operários", "Tarsila do Amaral", "1933"),
        pecaArtistica(R.drawable.obra3, "Abaporu", "arsila do Amaral", "1928")
    )

    var indexAtual by remember { mutableIntStateOf(0) }

    val imagemAtual = pecas[indexAtual]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painter = painterResource(id = imagemAtual.indexImagem),
            contentDescription = "Arte Imagem",
            modifier = Modifier
                .size(200.dp)
                .background(Color.White)
                .padding(10.dp)
        )
        Text(
            text = imagemAtual.titulo,
            fontSize = 20.sp
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = imagemAtual.autor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(text = "(" + imagemAtual.ano + ")")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                indexAtual = if (indexAtual > 0) indexAtual - 1 else pecas.size - 1
            }) {
                Text(text = "Anterior")
            }
            Button(onClick = {
                indexAtual = if (indexAtual < pecas.size - 1) indexAtual + 1 else 0
            }) {
                Text(text = "Próxima")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        GaleriaLayout()
    }
}