package guru.springframework.controllers;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    Iterable<Recipe> findRecipes(){
        return recipeRepository.findAll();
    }
}
