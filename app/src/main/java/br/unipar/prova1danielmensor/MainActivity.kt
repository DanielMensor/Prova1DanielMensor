package br.unipar.prova1danielmensor

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val listaDeTransacoes = mutableListOf<Item>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edDescricao = findViewById<EditText>(R.id.edDesc)
        val edValor = findViewById<EditText>(R.id.edValor)
        val cbDebito = findViewById<CheckBox>(R.id.cbDebito)
        val cbCredito = findViewById<CheckBox>(R.id.cbCredito)
        val listView = findViewById<ListView>(R.id.listview)
        val btnlancar = findViewById<Button>(R.id.btnInserir)
        val txtSaldo = findViewById<TextView>(R.id.txtSaldo)
        var saldo: Double = 0.0

        val adapter = Adapter(this,listaDeTransacoes)

        listView.adapter = adapter

        cbDebito.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                cbCredito.isChecked = false
            } else {
                cbCredito.isChecked = true
            }
        }
        cbCredito.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                cbDebito.isChecked = false
            } else {
                cbDebito.isChecked = true
            }
        }

        btnlancar.setOnClickListener{

            val descricao = edDescricao.text.toString()
            val valor = edValor.text.toString()
            val debito= cbDebito.isChecked
            val credito = cbCredito.isChecked
            var tipo:String = ""

            if(debito==true){
                tipo="Débito"
                saldo -=valor.toDouble()
            }
            if(credito==true){
                tipo="Crédito"
                saldo +=valor.toDouble()
            }


            if(descricao.isNotEmpty()&&valor.isNotEmpty()&&tipo!=""){

                val novaTrasacao = Item(descricao,valor,tipo)



                listaDeTransacoes.add(novaTrasacao)
                adapter.notifyDataSetChanged()

                txtSaldo.text = saldo.toString()


            }
            if(saldo>=0){
               txtSaldo.setTextColor(Color.parseColor("#0C7300"))
            }else{
                txtSaldo.setTextColor(Color.parseColor("#FF0000"))
            }


        }


    }
}