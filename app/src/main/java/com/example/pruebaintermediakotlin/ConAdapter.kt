package com.example.pruebaintermediakotlin


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebaintermediakotlin.databinding.FragmentSecondBinding
import com.example.pruebaintermediakotlin.model.Consumption


class ConAdapter: RecyclerView.Adapter<ConAdapter.ConVH>() {

    private var milistCon = listOf<Consumption>()
    private val selectedConsumption = MutableLiveData<Consumption>()

    fun selectedItem(): LiveData<Consumption> = selectedConsumption

    fun update(listaCon: List<Consumption>){
        milistCon= listaCon
        notifyDataSetChanged()
    }

    inner  class ConVH(private val binding : FragmentSecondBinding ): RecyclerView.ViewHolder(binding.root),
            View.OnClickListener{
        fun bind(con:Consumption){
            binding.textNombre.text= con.item
            binding.textCantidadVista.text = con.queantity.toString()
            binding.textTotalPagado.text = con.total.toString()
            itemView.setOnClickListener(this)



        }

        override fun onClick(p0: View?) {
            selectedConsumption.value = milistCon[adapterPosition]
        }


    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConVH {
        return ConVH(FragmentSecondBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ConVH, position: Int) {
        val task = milistCon[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = milistCon.size


}