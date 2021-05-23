package com.example.recipe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.recipe.databinding.RecipeLayoutAdapterBinding
import com.example.recipe.fragments.HomeFragmentDirections
import com.example.recipe.models.Result

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(val binding: RecipeLayoutAdapterBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    private val differ = AsyncListDiffer(this, differCallBack)
    var recipe: List<Result>
        get() = differ.currentList
        set(value) {
            differ.submitList(value)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
       return RecipeViewHolder(
           RecipeLayoutAdapterBinding.inflate(
               LayoutInflater.from(parent.context),parent,false
           )
       )
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currRecipe = recipe[position]
        holder.binding.apply {
            tvTitleRecipe.text = currRecipe.title
            imageView.load(currRecipe.thumbnail){
                crossfade(true)
                crossfade(1000)
            }
        }
        holder.itemView.setOnClickListener {mView ->
            val direction = HomeFragmentDirections
                .actionHomeFragmentToDetailsFragment(currRecipe)
            mView.findNavController().navigate(direction)
        }
    }

    override fun getItemCount()= recipe.size



}