package de.erdbeerbaerlp.cfcore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public enum CFFileRelationType{
    Unknown(-1),
    EmbeddedLibrary(1),
    OptionalDependency(2),
    RequiredDependency(3),
    Tool(4),
    Incompatible(5),
    Include(6);

    public int value;

    CFFileRelationType(int i) {
        this.value = i;
    }
    
    public static CFFileRelationType fromValue(int val) {
        for(CFFileRelationType type : CFFileRelationType.values()) {
            if(type.value == val) {
                return type;
            }
        }
        return CFFileRelationType.Unknown;
    }

    public static class FileRelationTypeDeserializer implements JsonDeserializer<CFFileRelationType> {
        @Override
        public CFFileRelationType deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            int value = element.getAsInt();
            return CFFileRelationType.fromValue(value);
        }
    }
}