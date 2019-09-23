package com.technologies.zenlight.defaultapp.userInterface.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.technologies.zenlight.defaultapp.data.AppDataManager
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : ViewModel> : Fragment() {

    @Inject
    lateinit var dataManager: AppDataManager

    var baseActivity: BaseActivity<*, *>? = null
        private set

    private var mRootView: View? = null
    private var mViewModel: V? = null

    lateinit var dataBinding: T
        private set


    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract var bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract var layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V?

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is BaseActivity<*, *>) {
            val activity = context as BaseActivity<*, *>?
            this.baseActivity = activity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        mRootView = dataBinding.root
        mViewModel = viewModel
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.setVariable(bindingVariable, mViewModel)
        dataBinding.executePendingBindings()
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    protected fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }

//    protected fun setIdlingResource(isIdle: Boolean, tag: String) {
//
//        if (baseActivity != null && AppFlavorConstants.type == AppFlavorConstants.Type.DEVELOPMENT) {
//            baseActivity!!.setIdlingResource(isIdle, tag)
//        }
//    }
}