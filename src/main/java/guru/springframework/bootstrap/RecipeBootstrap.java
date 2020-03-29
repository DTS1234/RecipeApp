package guru.springframework.bootstrap;

import guru.springframework.model.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeBootstrap(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                            RecipeRepository recipeRepository){
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOM's
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> unitUomOptional = unitOfMeasureRepository.findByDescription("Unit");

        if(!unitUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> poundUomOptional = unitOfMeasureRepository.findByDescription("Pound");

        if(!poundUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> cupsUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupsUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");

        if(!ounceUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM Not Found");
        }

        //get optional's
        UnitOfMeasure each = eachUomOptional.get();
        UnitOfMeasure tableSpoon = tableSpoonUomOptional.get();
        UnitOfMeasure teaSpoon = teaSpoonUomOptional.get();
        UnitOfMeasure pound= poundUomOptional.get();
        UnitOfMeasure pint = pintUomOptional.get();
        UnitOfMeasure cup = cupsUomOptional.get();
        UnitOfMeasure ounce = ounceUomOptional.get();
        UnitOfMeasure unit = unitUomOptional.get();

        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        Optional<Category> fastFoodCategoryOptional = categoryRepository.findByDescription("Fast Food");

        if(!italianCategoryOptional.isPresent())
            throw new RuntimeException("Expected Category Not Found");

        Category italianCategory = italianCategoryOptional.get();
        Category fastFoodCategory = fastFoodCategoryOptional.get();

        Recipe carbonaraRecipe = new Recipe();

        Notes carbonaraNotes = new Notes(carbonaraRecipe,"This recipe uses raw eggs, which are essentially " +
                "cooked by tossing with hot pasta. " +
                "They are not cooked to the point of scrambled though, just enough to thicken the eggs into a sauce.");

        carbonaraRecipe.setNotes(carbonaraNotes);
        carbonaraRecipe.setUrl("https://www.simplyrecipes.com/recipes/spaghetti_alla_carbonara/");
        carbonaraRecipe.setServings(4);
        carbonaraRecipe.setSource("simple recipes");

        StringBuilder directions = new StringBuilder();
        directions.append("Put a large pot of salted water on to boil (1 Tbsp salt for every 2 quarts of water.)");
        directions.append("While the water is coming to a boil, heat the olive oil in a large sauté pan over medium heat.");
        directions.append("Add the bacon or pancetta and cook slowly until crispy.");
        directions.append("Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and " +
                "garlic into a large bowl.");
        directions.append(("Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot."));
        directions.append("It's the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce.");

        carbonaraRecipe.setDirections(directions.toString());
        carbonaraRecipe.getCategories().add(italianCategory);
        carbonaraRecipe.getCategories().add(fastFoodCategory);
        carbonaraRecipe.setCookTime(15);
        carbonaraRecipe.setPrepTime(10);
        carbonaraRecipe.setDifficulty(Difficulty.EASY);

        carbonaraRecipe.addIngredient(new Ingredient("extra virgin olive oil", new BigDecimal(1), tableSpoon));
        carbonaraRecipe.addIngredient(new Ingredient("spaghetti pasta", new BigDecimal(1), pound));
        carbonaraRecipe.addIngredient(new Ingredient("grated parmesan", new BigDecimal(1), cup));
        carbonaraRecipe.addIngredient(new Ingredient("pancetta", new BigDecimal(0.5), pound));
        carbonaraRecipe.addIngredient(new Ingredient("salt", new BigDecimal(1), teaSpoon));
        carbonaraRecipe.addIngredient(new Ingredient("whole eggs", new BigDecimal(3), unit));

        carbonaraRecipe.setDescription("Spaghetti Pasta Carbonara");

        recipes.add(carbonaraRecipe);

        return recipes;

    }

}
