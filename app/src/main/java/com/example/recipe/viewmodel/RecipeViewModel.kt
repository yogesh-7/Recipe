package com.example.recipe.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipe.models.RecipeResponse
import com.example.recipe.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel
    @Inject constructor(private val repository: RecipeRepository) :ViewModel() {

        private val _response = MutableLiveData<RecipeResponse>()

    val recipeResponse : LiveData<RecipeResponse>
    get()= _response

    init {
        getRecipe()
    }

    private fun getRecipe() = viewModelScope.launch {
        repository.getRecipe().let {
            response ->

            if(response.isSuccessful){
               _response.postValue(response.body())
            }else{
                Log.d("response Error","getRecipe:  ${response.code()} ")
            }

        }
    }
}




