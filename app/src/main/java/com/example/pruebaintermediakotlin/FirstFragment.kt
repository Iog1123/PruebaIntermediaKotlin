package com.example.pruebaintermediakotlin

import android.content.ClipData
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.pruebaintermediakotlin.databinding.FragmentFirstBinding
import com.example.pruebaintermediakotlin.model.Consumption
import kotlinx.coroutines.newFixedThreadPoolContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
private  lateinit var binding: FragmentFirstBinding
    private val viewModel: ConViewModel by activityViewModels()
    private var idConsumption:Int=0
    private var consumptionSelected: Consumption?= null
    override fun onCreateView(

            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding= FragmentFirstBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val picker= binding.ContenedorCantidad
        picker.maxValue=30
        picker.minValue=1


        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.NombreItem.setText(it.item)
                binding.ContenedorPrecioU.setText(it.itemPrice)
                binding.ContenedorCantidad.value.toString().toInt(it.queantity)
                Log.d("ID", it.id.toString())
                idConsumption = it.id
                consumptionSelected = it
            }
        })


        picker.setOnValueChangedListener{ NumberPicker,i,i2 ->
            Toast.makeText(requireContext(),NumberPicker.value.toString(), Toast.LENGTH_LONG).show()

           var  Cantidad = binding.ContenedorCantidad.value.toString().toInt()
            var Precio= binding.ContenedorPrecioU.text.toString().toInt()

            var total = Cantidad * Precio
            binding.TextTotalPagar.setText(total.toString())

        }



        binding.buttonGuardar.setOnClickListener{
            var Cantidad= binding.ContenedorCantidad.value.toString().toInt()
            var precio: Int=  binding.ContenedorPrecioU.text.toString().toInt()
            var total = binding.TextTotalPagar.text.toString().toInt()
            var nombreItem= binding.NombreItem.text.toString()

            saveRegistro(Cantidad,precio,total,nombreItem)
            viewModel.selected(null)
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }



    }


    private fun saveRegistro(Cantidad: Int,precio: Int,total: Int,nombreItem: String) {


        if (nombreItem.isEmpty() && precio==0 && Cantidad ==0) {
            Toast.makeText(context, "Debes añadir los datos", Toast.LENGTH_LONG).show()
        } else {
            if (idConsumption == 0) {
                val newConsumption = Consumption(

                        item = nombreItem,
                        itemPrice = precio.toInt(),
                        queantity = Cantidad.toInt(),
                        total = total)
                viewModel.insertConsumption(newConsumption)

            } else {
                val newConsumption = Consumption(
                        id = idConsumption,
                        item = nombreItem,
                        itemPrice = precio.toInt(),
                        queantity= Cantidad.toInt(),
                        total = total)
                viewModel.insertConsumption(newConsumption)
            }
        }

    }
    }