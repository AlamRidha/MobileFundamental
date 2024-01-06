package com.example.mobilefundamental

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment

class OptionDialogFragment : DialogFragment() {
    private lateinit var btnChoose: Button
    private lateinit var btnClose: Button
    private lateinit var rgOptions: RadioGroup
    private lateinit var rbSc: RadioButton
    private lateinit var rbLj: RadioButton
    private lateinit var rbKd: RadioButton
    private lateinit var rbJh: RadioButton
    private var optionDialogListener: OnOptionDialogListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option_dialog, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        val fragment = parentFragment
        if (fragment is DetailCategoryFragment) {
            this.optionDialogListener = fragment.optionDialogListener
        }
    }

    override fun onDetach() {
        super.onDetach()
        this.optionDialogListener = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnChoose = view.findViewById(R.id.btn_choose)
        btnClose = view.findViewById(R.id.btn_close)
        rgOptions = view.findViewById(R.id.rg_options)
        rbSc = view.findViewById(R.id.rb_sc)
        rbLj = view.findViewById(R.id.rb_lj)
        rbKd = view.findViewById(R.id.rb_kd)
        rbJh = view.findViewById(R.id.rb_jh)

        btnChoose.setOnClickListener {
            val checkedRadioButtonId = rgOptions.checkedRadioButtonId
            if (checkedRadioButtonId != -1) {
                var coach: String? = when (checkedRadioButtonId) {
                    R.id.rb_sc -> rbSc.text.toString().trim()
                    R.id.rb_lj -> rbLj.text.toString().trim()
                    R.id.rb_kd -> rbKd.text.toString().trim()
                    R.id.rb_jh -> rbJh.text.toString().trim()
                    else -> null
                }
                optionDialogListener?.onOptionChosen(coach)
                dialog?.dismiss()
            }
        }

        btnClose.setOnClickListener {
            dialog?.cancel()
        }
    }

    interface OnOptionDialogListener {
        fun onOptionChosen(text: String?)
    }
}

