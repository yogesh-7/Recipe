package com.example.recipe.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.recipe.R
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.recipe.adapters.RecipeAdapter
import com.example.recipe.databinding.FragmentHomeBinding
import com.example.recipe.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding :FragmentHomeBinding? =null
    private val binding get() = _binding!!
    private val viewModel: RecipeViewModel by viewModels()
    private lateinit var recipeAdapterv: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRv()

    }

    private fun setUpRv(){

        recipeAdapterv = RecipeAdapter()

        binding.rvRecipe.apply {
            layoutManager = GridLayoutManager(requireContext(),2)
            setHasFixedSize(true)
            adapter =recipeAdapterv

        }

        viewModel.recipeResponse.observe(requireActivity(),{response ->
             recipeAdapterv.recipe = response.results

        })


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}