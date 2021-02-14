package com.example.pruebaintermediakotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebaintermediakotlin.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {
 private lateinit var binding: FragmentSecondBinding
    private val viewModel: ConViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding= FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter= ConAdapter()

        binding.recyclerView4.adapter= adapter
        binding.recyclerView4.layoutManager= LinearLayoutManager(context)

        viewModel.allConsumption.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        binding.btnVolver.setOnClickListener{
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        adapter.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.d("ITEM SELECTED", it.item)

                viewModel.selected(it)
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        })
    }
}