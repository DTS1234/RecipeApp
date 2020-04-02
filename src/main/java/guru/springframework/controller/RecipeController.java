package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import guru.springframework.util.Mappings;
import guru.springframework.util.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService recipeService;
   
    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping(Mappings.SHOW_RECIPE+"/{id}")
    public String showRecipeById(@PathVariable String id, Model model){

        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);

        return ViewNames.SHOW_RECIPE;
    }

    @RequestMapping(Mappings.NEW_RECIPE)
    public String newRecipe(Model model){

        model.addAttribute("recipe", new RecipeCommand());

        return ViewNames.NEW_RECIPE;
    }

    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/" + Mappings.SHOW_RECIPE +"/"+ savedCommand.getId();
    }

}
