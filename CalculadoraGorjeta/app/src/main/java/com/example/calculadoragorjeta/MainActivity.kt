package com.example.calculadoragorjeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import java.text.NumberFormat

class MainActivity : AppCompatActivity(), View.OnFocusChangeListener, SeekBar.OnSeekBarChangeListener {

    private lateinit var txtTotalConta: EditText
    private lateinit var txtPessoas: EditText
    private lateinit var skGorjeta: SeekBar

    private val formatador = NumberFormat.getCurrencyInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTotalConta = this.findViewById(R.id.txtTotalConta)
        txtTotalConta.onFocusChangeListener = this

        txtPessoas = this.findViewById(R.id.txtPessoas)
        txtPessoas.onFocusChangeListener = this

        skGorjeta = this.findViewById(R.id.skGorjeta)
        skGorjeta.setOnSeekBarChangeListener(this)

        formatador.maximumFractionDigits = 2
    }

    private fun atualizaDadosConta() {
        if(txtTotalConta.text.toString().isNotEmpty()
            && txtPessoas.text.toString().isNotEmpty()) {

            val valorConta = txtTotalConta.text.toString().toDouble()
            val qtdPessoas = txtPessoas.text.toString().toInt()

            val lblValorGorjeta: TextView = this.findViewById(R.id.lblValorGorjeta)
            val valorRealGorjeta = valorConta * skGorjeta.progress / 100
            lblValorGorjeta.text = formatador.format(valorRealGorjeta)

            val totalConta = valorConta + valorRealGorjeta

            val lblValorRealConta: TextView = this.findViewById(R.id.lblValorTotal)
            lblValorRealConta.text = formatador.format(totalConta)

            val lblValorPorPessoa: TextView = this.findViewById(R.id.lblValorPorPessoa)
            lblValorPorPessoa.text = formatador.format(totalConta/qtdPessoas)
        }
    }

    override fun onFocusChange(p0: View?, p1: Boolean) {
        this.atualizaDadosConta()
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        val lblPercentualGorjeta: TextView = this.findViewById(R.id.lblPercentualGorjeta)
        val textLbl = skGorjeta.progress.toString() + "%"
        lblPercentualGorjeta.text = textLbl

        this.atualizaDadosConta()
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
    }

}