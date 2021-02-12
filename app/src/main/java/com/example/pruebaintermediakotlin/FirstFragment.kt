package com.example.pruebaintermediakotlin

import android.content.ClipData
import android.os.Bundle
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

        picker.setOnValueChangedListener{ NumberPicker,i,i2 ->
            Toast.makeText(requireContext(),NumberPicker.value.toString(), Toast.LENGTH_LONG).show()
        }

        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            binding.NombreItem.setText(it.item)
            binding.ContenedorPrecioU.setText(it.itemPrice)
            binding.ContenedorCantidad.value= it.queantity
            binding.TextTotalPagar.setText(it.total)
        })

        binding.buttonGuardar.setOnClickListener{
            val Precio=binding.ContenedorPrecioU.text.toString().toInt()
            val Cantidad= binding.ContenedorCantidad.value.toString().toInt()

            val Total= Precio*Cantidad
            binding.TextTotalPagar.setText(Total.toString())


            saveData()
            viewModel.selected(null)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }



    }

    private fun saveData() {
        val nombreItem = binding.NombreItem.toString()
        val precio = binding.ContenedorPrecioU.toString()
        val cantidad = binding.ContenedorCantidad.toString()
        val total= binding.TextTotalPagar.toString().toInt()


        if (nombreItem.isEmpty() && precio.isEmpty() && cantidad.isEmpty()) {
            Toast.makeText(context, "Debes añadir los datos", Toast.LENGTH_LONG).show()
        } else {
            if (idConsumption == 0) {
                val newConsumption = Consumption(

                        item = nombreItem,
                        itemPrice = precio.toInt(),
                        queantity = cantidad.toInt(),
                        total = total)
                viewModel.insertConsumption(newConsumption)

            } else {
                val newConsumption = Consumption(
                        id = idConsumption,
                        item = nombreItem,
                        itemPrice = precio.toInt(),
                        queantity= cantidad.toInt(),
                        total = total)
                viewModel.insertConsumption(newConsumption)
            }
        }

    }
    }