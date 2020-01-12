package com.example.nhlstats.common.presentation

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.Composable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.ui.core.setContent
import androidx.ui.graphics.Color
import androidx.ui.material.ColorPalette
import androidx.ui.material.MaterialTheme
import com.example.nhlstats.Navigator
import com.example.nhlstats.R
import org.koin.android.viewmodel.ext.android.getViewModel
import kotlin.reflect.KClass

abstract class BaseFragment<out VM : BaseViewModel>(viewModelClass: KClass<VM>) : Fragment() {

    protected open val viewModel: VM by lazy { getViewModel<VM>(viewModelClass) }

    companion object {
        val ID = View.generateViewId()
    }


    final override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = FrameLayout(requireContext())
        root.id = ID
        val context = requireContext()
        (root as ViewGroup).setContent {
            MaterialTheme(
                colors = ColorPalette(
                    primary = Color(
                        ContextCompat.getColor(
                            context,
                            R.color.colorPrimary
                        )
                    ),
                    secondary = Color(
                        ContextCompat.getColor(
                            context,
                            R.color.colorPrimaryDark
                        )
                    )

                )
            ) {
                drawComposeView()
                viewModel.onActive()
            }

        }



        return root
    }


    protected fun navigate(fragment: Fragment, params: Parcelable? = null) {
        (activity as Navigator).navigate(fragment = fragment)
    }

    @Composable
    abstract fun drawComposeView()

}