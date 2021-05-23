package com.example.recipe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.example.recipe.R
import com.example.recipe.databinding.FragmentWebViewBinding
import com.example.recipe.models.Result


class WebViewFragment : Fragment(R.layout.fragment_web_view) {

    private var _binding :FragmentWebViewBinding? =null
    private val binding get() = _binding!!
    private val args :WebViewFragmentArgs by navArgs()
    private lateinit var recipe : Result



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWebViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipe = args.recipeWebView
        setUpWebView()
    }

    private fun setUpWebView() {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(recipe.href)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}