package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.model.Category;
import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;

public class CategoryToCategoryCommand implements Converter<Category, CategoryCommand> {

    @Synchronized
    @Nullable
    @Override
    public CategoryCommand convert(Category category) {

        if (category == null){
            return null;
        }

        final CategoryCommand categoryCommand = new CategoryCommand();

        categoryCommand.setId(category.getId());
        categoryCommand.setDescription(category.getDescription());

        return categoryCommand;
    }
}
