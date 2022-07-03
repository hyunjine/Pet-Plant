package com.hyunjine.petplant.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, R: BaseViewModel>: Fragment() {

    protected lateinit var navController : NavController
    private var _binding: T? = null
    protected val binding get() = _binding!!
    protected lateinit var viewModel: R
    abstract fun setViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setBinding(inflater, container)
        return binding.root
    }

    abstract fun setBinding(lf: LayoutInflater, ct: ViewGroup?) : T

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(binding.root)
        initView()
    }

    abstract fun initView()

    protected fun startFragment(direction: NavDirections) = navController.navigate(direction)

    protected fun backStack() = navController.popBackStack()

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}