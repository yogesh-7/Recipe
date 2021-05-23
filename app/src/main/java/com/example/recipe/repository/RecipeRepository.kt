package com.example.recipe.repository

import com.example.recipe.api.ApiService
import javax.inject.Inject

class RecipeRepository
@Inject constructor(private  val apiService: ApiService)
{

    suspend fun getRecipe() = apiService.getRecipe()

}