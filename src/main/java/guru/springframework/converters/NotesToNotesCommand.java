package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.model.Notes;
import jdk.internal.jline.internal.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes notes) {

        if(notes != null){

            final NotesCommand notesCommand = new NotesCommand();
            notesCommand.setId(notes.getId());
            notesCommand.setRecipeNotes(notes.getRecipeNotes());

            return notesCommand;
        }

        return null;
    }
}