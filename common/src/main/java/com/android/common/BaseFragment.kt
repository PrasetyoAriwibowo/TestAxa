package com.android.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData

abstract class BaseFragment<VM : BaseViewModel, Binding : ViewDataBinding> : Fragment() {
    abstract val vm: VM
    lateinit var binding: Binding
    abstract val layoutResourceId: Int


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<Binding>(inflater, layoutResourceId, container, false)
        binding.setVariable(BR.vm, vm)
        binding.lifecycleOwner = this
        initBinding(binding)
        return binding.root
    }

    open fun initBinding(binding: Binding) {}

    fun <T> observeResponseData(
        data: MutableLiveData<AppResponse<T>>,
        success: ((T) -> Unit),
        error: ((Throwable) -> Unit)?,
        loading: (() -> Unit)? = {}
    ) {
        data.observe(this) { response ->
            when (response) {
                is SuccessResponse -> {
                    response.data?.let {
                        success?.invoke(it)
                    }
                }
                is ErrorResponse -> {
                    response.error?.let {
                        error?.invoke(it)
                    }
                }
                is LoadingResponse -> {
                    loading?.invoke()
                }
            }
        }
    }
}