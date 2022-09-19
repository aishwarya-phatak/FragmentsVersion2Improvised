package com.example.fragmentsversion2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fragmentsversion2.databinding.CounterFragmentBinding

class CounterFragment : Fragment(){

    private lateinit var binding: CounterFragmentBinding

    var count = 0
    set(value) {
        field = value
        binding.txtCount.text = "$count"
    }

    var title : String = ""
    set(value) {
        field = value
        binding.txtTitle.text = value
    }

    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CounterFragmentBinding.inflate(inflater)

        if(arguments != null) {
            binding.txtTitle.text = arguments!!.getString("title")

            binding.txtCount.text = "${arguments!!.getInt("count")}"
        }
        initListeners()
        return binding.root
    }

    private fun initListeners(){
        binding.btnIncrementCount.setOnClickListener {
            binding.txtCount.text = "${++count}"
        }

        binding.btnDecrementCount.setOnClickListener {
            binding.txtCount.text = "${--count}"
        }
    }
}