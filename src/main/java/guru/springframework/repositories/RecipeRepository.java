package guru.springframework.repositories;

import guru.springframework.model.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {

    List<Recipe> findAll();

}
