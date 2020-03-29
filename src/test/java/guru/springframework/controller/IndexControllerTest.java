package guru.springframework.controller;

import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import guru.springframework.util.ViewNames;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IndexControllerTest {

    @Mock
    RecipeService recipeService;
    @Mock
    Model model;

    IndexController indexController;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);

    }

    @Test
    public void getIndexPageTest() {

        //given
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes.add(new Recipe());
        recipes.add(new Recipe());

        when(recipeService.getRecipes()).thenReturn(recipes);
        ArgumentCaptor<List<Recipe>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        //when
        String result = indexController.getIndexPage(model);

        //then
        assertEquals(ViewNames.INDEX, result);
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        verify(recipeService, times(1)).getRecipes();
        List<Recipe> listInController = argumentCaptor.getValue();
        assertEquals(listInController.size(),2);

    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNames.INDEX));
    }

}