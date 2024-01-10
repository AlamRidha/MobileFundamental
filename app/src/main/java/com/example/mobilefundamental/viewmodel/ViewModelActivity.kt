package com.example.mobilefundamental.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mobilefundamental.R
import com.example.mobilefundamental.databinding.ActivityViewModelBinding

class ViewModelActivity : AppCompatActivity() {

    // declaration for component binding and viewmodel
    private lateinit var binding: ActivityViewModelBinding
    private lateinit var viewModel: MainViewModel

    //  declaration for viewmodel have simple code
    //  private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewModelBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  initialization for viewmodel
        //  if use the simple code, comment this line
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        //  call displayResult function
        displayResult()

        //  get data from editText and convert to string then call the function from viewmodel
        binding.btnCalculate.setOnClickListener {
            val width = binding.edtWidth.text.toString()
            val height = binding.edtHeight.text.toString()
            val length = binding.edtLength.text.toString()

            when {
                width.isEmpty() -> {
                    binding.edtWidth.error = "Field masih kosong"
                }

                height.isEmpty() -> {
                    binding.edtHeight.error = "Field masih kosong"
                }

                length.isEmpty() -> {
                    binding.edtLength.error = "Field masih kosong"
                }

                else -> {
                    viewModel.calculate(width, height, length)
                    displayResult()
                }

            }
        }
    }

    //  make function for displayResult
    private fun displayResult() {
        binding.tvResult.text = viewModel.result.toString()
    }
}