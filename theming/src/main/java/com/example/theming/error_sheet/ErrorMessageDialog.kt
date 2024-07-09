package com.example.theming.error_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.theming.R
import com.example.theming.databinding.FragmentErrorMessageDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ErrorMessageDialog : BottomSheetDialogFragment() {

    private var listener: (() -> Unit)? = null
    private var buttonName: String? = null
    private var message: String? = null

    private var mBinding: FragmentErrorMessageDialogBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding  = FragmentErrorMessageDialogBinding.inflate(inflater,container,false)

        return mBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonName?.let {
            mBinding?.btnAction?.text = it
        }
        mBinding?.message?.text = message

        mBinding?.btnAction?.setOnClickListener {
            listener?.invoke()
            dismiss()
        }

    }
    companion object {
        @JvmStatic
        fun newInstance(buttonName:String? = null ,msg:String, listener: (() -> Unit)?) =
            ErrorMessageDialog().apply {
                this.listener = listener
                this.buttonName = buttonName
                this.message = msg
            }
    }

    override fun getTheme(): Int {
        return R.style.RoundBottomSheetDialogTheme
    }

}