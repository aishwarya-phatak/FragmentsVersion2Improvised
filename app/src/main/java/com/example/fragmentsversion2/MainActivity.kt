package com.example.fragmentsversion2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.example.fragmentsversion2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var counterFragment: CounterFragment
    private lateinit var binding: ActivityMainBinding
    private var counterFragmentList = ArrayList<CounterFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragments()
        initListeners()
    }

    private fun initFragments(){
        counterFragment = supportFragmentManager.findFragmentById(R.id.counterFragment) as CounterFragment
    }

    private fun initListeners(){
        binding.btnResetCount.setOnClickListener {
            counterFragment.count = binding.edtCount.text.toString().toInt()

            for(counterFragment in counterFragmentList){
                counterFragment.count = binding.edtCount.text.toString().toInt()

            }
        }

        binding.btnAddCounterFragment.setOnClickListener {
                //adding the counter Fragment
            var counterFragment = CounterFragment()

            var input = Bundle()                    //providing input to your fragment
            input.putString("title",binding.edtCounterFragmentTitle.text.toString())
            input.putInt("count",binding.edtCount.text.toString().toInt())
            counterFragment.arguments = input

            //Fragment Transaction
            var fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.mainContainer,counterFragment,null)
            fragmentTransaction.commit()

            counterFragmentList.add(counterFragment)
        }

        binding.btnRemoveCounterFragment.setOnClickListener {
            if(counterFragmentList.size == 0){
                return@setOnClickListener
            }
            //removing the counter Fragment
            supportFragmentManager.beginTransaction()
                .remove(counterFragmentList[0])
                .commit()

            counterFragmentList.removeAt(0)
        }
    }

    override fun onStart() {
        super.onStart()
        counterFragment.count = 0
    }
}