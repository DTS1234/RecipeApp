package guru.springframework.service;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Recipe;

import java.util.List;

public interface RecipeService {

    List<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
