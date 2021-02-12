package com.example.pruebaintermediakotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.example.pruebaintermediakotlin.databinding.FragmentFirstBinding
import com.example.pruebaintermediakotlin.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
   private lateinit var  binding: FragmentSecondBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
        // Inflate the layout for this fragment

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        /*  val list: MutableList<String> = mutableListOf()
          for(i in 1..30){
            list.add("$i ")
        }

        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(),R.layout.support_simple_spinner_dropdown_item,list)
        binding.spinner.adapter= arrayAdapter

       binding.spinner.setOnItemClickListener{ AdapterView, view,i,l ->
           val selected = AdapterView.onItemSelectedListener
           Toast.makeText(requireContext(),selected.toString(),Toast.LENGTH_LONG).show()

       }*/




        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}