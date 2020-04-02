package guru.springframework.controller;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.model.Recipe;
import guru.springframework.service.RecipeService;
import guru.springframework.util.Mappings;
import guru.springframework.util.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping(Mappings.SHOW_RECIPE)
    public String showRecipeById(@PathVariable String id, Model model){
        Recipe recipe = recipeService.findById(Long.valueOf(id));
        model.addAttribute("recipe", recipe);
        return ViewNames.SHOW_RECIPE;
    }

    @GetMapping
    @RequestMapping(Mappings.NEW_RECIPE)
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());
        return ViewNames.NEW_RECIPE;
    }

    @PostMapping
    @RequestMapping(Mappings.RECIPE)
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return Mappings.REDIRECT_SHOW + savedCommand.getId();
    }

    @GetMapping
    @RequestMapping(Mappings.UPDATE)
    public String updateRecipe(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
        return ViewNames.NEW_RECIPE;
    }

    @GetMapping
    @RequestMapping(Mappings.DELETE)
    public String deleteRecipe(@PathVariable Long id){
        recipeService.deleteById(id);
        return "redirect:/";
    }

}
