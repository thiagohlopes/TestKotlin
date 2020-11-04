package com.example.alcoolougasolina

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    var valorGasolina = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Carregando componentes
        val seekbar = findViewById<SeekBar>(R.id.seekBar)
        val txtGasolina = findViewById<TextView>(R.id.tv_valorGasolina)
        val txtResult = findViewById<TextView>(R.id.tv_result)
        val btCalcular = findViewById<Button>(R.id.bt_calcular)

        //tamanho seekBar
        seekbar.max = 1000 //vai contar até 10, mas com centavos

        //definir listener para seekBar
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                valorGasolina = p1
                var texto = "R$ "
                texto += formataValor(valorGasolina/100.0)
                txtGasolina.text = texto
            }

            //quando começar mexer
            override fun onStartTrackingTouch(p0: SeekBar?) {
                txtResult.text = "Selecione o valor da gasolina"
            }

            //quando parar de mexer
            override fun onStopTrackingTouch(p0: SeekBar?) {
                txtResult.text = "Clique em calcular para obter resultado"
            }

        })

        //Listener do botao
        btCalcular.setOnClickListener {
            var valorResult = (valorGasolina * 0.7)/100.0
            var texto ="Abasteça com Alcool se ele custar até: R$ "
            texto += formataValor(valorResult)
            txtResult.text = texto
        }
    }
    //locale france para usar virgula, format  numero de casas decimais, valor é variaveç
    private fun formataValor(valor: Double): Any? {
        return String.format(Locale.FRANCE, "%.2f", valor)
    }
}