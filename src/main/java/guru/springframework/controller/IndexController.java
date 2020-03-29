package guru.springframework.controller;

import guru.springframework.service.RecipeService;
import guru.springframework.util.Mappings;
import guru.springframework.util.ViewNames;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService){
        this.recipeService = recipeService;
    }

    @RequestMapping(Mappings.INDEX)
    public String getIndexPage(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        return ViewNames.INDEX;
    }





}
