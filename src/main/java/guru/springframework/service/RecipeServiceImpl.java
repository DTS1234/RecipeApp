package guru.springframework.service;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {

    private RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>();
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);

        return recipes;

    }

    @Override
    public Recipe findById(Long id) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw  new RuntimeException("Recipe not found !");
        }

        return recipeOptional.get();
    }


}
