package guru.springframework.service;

import guru.springframework.model.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipeByIdTest() throws Exception{

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(recipe.getId())).thenReturn(recipeOptional);
        Recipe recipeReturned = recipeService.findById(1L);

        assertNotNull("Null returned", recipeReturned);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();

    }

    @Test(expected = RuntimeException.class)
    public void getNullRecipeByIdTest(){
        recipeService.findById(3L);
    }

    @Test
    public void getRecipes() throws Exception{

        Recipe recipe = new Recipe();
        List<Recipe> recipeList = new ArrayList<>();
        recipeList.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeList);

        List<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();

    }
}