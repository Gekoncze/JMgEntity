package cz.mg.entity.validator.utilities;

import cz.mg.annotations.classes.Utility;
import cz.mg.annotations.requirement.Mandatory;
import cz.mg.annotations.storage.Part;
import cz.mg.collections.array.Array;


public @Utility class CharacterValidationUtility {
    private final @Mandatory @Part Array map = new Array(255);

    public CharacterValidationUtility(@Mandatory Character... validCharacters) {
        for (char validCharacter : validCharacters) {
            map.set(true, validCharacter);
        }
    }

    public boolean isValid(char ch) {
        if (ch < 255) {
            return map.get(ch) != null;
        } else {
            return false;
        }
    }
}
