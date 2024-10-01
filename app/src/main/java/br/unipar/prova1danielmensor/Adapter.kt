package br.unipar.prova1danielmensor

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView

class Adapter (
    private val context: Context,
    private val listaValor: MutableList<Item>): ArrayAdapter<Item>(context,0,listaValor){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val item = listaValor.get(position)

        val view = LayoutInflater.from(context).inflate(R.layout.itemlista, parent, false)

        val descricao = view.findViewById<TextView>(R.id.txtDesc)
        val valor = view.findViewById<TextView>(R.id.txtValor)
        val tipo = view.findViewById<TextView>(R.id.txtTipo)



        descricao.setText(item.descricao)
        valor.setText(item.valor.toString())
        tipo.setText(item.tipo)

        if(item.tipo=="Débito"){
            valor.setTextColor(Color.parseColor("#FF0000"))
        }else{
            valor.setTextColor(Color.parseColor("#0C7300"))
        }

        return view

    }


}




