package com.example.recipe.api

import com.example.recipe.models.RecipeResponse
import com.example.recipe.util.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {


    @GET(END_POINT)
    suspend fun getRecipe():Response<RecipeResponse>
}