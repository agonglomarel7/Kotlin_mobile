package com.example.androidtp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

class OrderActivity : AppCompatActivity() {

    private val recipes:ArrayList<RecipeData> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        loadRecipes()
}   public fun goToOrders(view: View)
    {
        finish();
    }
    private fun loadRecipes(){
        Log.d("OrderActivity", "on est dans l'API avec code ")
        Api().get<List<RecipeData>>(
            "https://mypizza.lesmoulinsdudev.com/recipes",
            onSuccess = { responseCode, loadedRecipes ->
                Log.d("OrderActivity", "Réponse de l'API avec code : $responseCode")
                if (loadedRecipes != null) {
                    Log.d("OrderActivity", "Recettes reçues : $loadedRecipes")
                }
                loadRecipesSuccess(responseCode, loadedRecipes)
            }
        )
    }
    private fun loadRecipesSuccess(responseCode:Int, loadedRecipes:List<RecipeData>?){
        if (responseCode == 200 && loadedRecipes != null){
            //on peut effacer toutes les données précédente avec ceci
            // recipes.clear()

            recipes.addAll(loadedRecipes)
            Toast.makeText(this,"Recettes mises à jour avec succès",   Toast.LENGTH_SHORT).show()
            Log.d("OrderActivity", "Recettes mises à jour avec succès : $recipes")
        }else {
            Toast.makeText(this,"Échec du chargement des recettes ou liste vide.",Toast.LENGTH_SHORT).show()
            Log.d("OrderActivity", "Échec du chargement des recettes ou liste vide.")
        }
    }


}