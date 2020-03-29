package guru.springframework.service;

import guru.springframework.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipes();
    Recipe findById(Long id);

}
