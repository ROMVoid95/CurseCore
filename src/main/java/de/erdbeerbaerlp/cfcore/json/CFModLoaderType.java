package de.erdbeerbaerlp.cfcore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public enum CFModLoaderType
{
    Unknown(-1),
    Any(0),
    Forge(1),
    Cauldron(2),
    LiteLoader(3),
    Fabric(4),
    Quilt(5);
    
    
    public final int value;

    CFModLoaderType(int i) {
        this.value = i;
    }
    public static CFModLoaderType fromValue(int val) {
        for(CFModLoaderType type : CFModLoaderType.values()) {
            if(type.value == val) {
                return type;
            }
        }
        return CFModLoaderType.Unknown;
    }

    public static class ModLoaderTypeDeserializer implements JsonDeserializer<CFModLoaderType> {
        @Override
        public CFModLoaderType deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            int value = element.getAsInt();
            return CFModLoaderType.fromValue(value);
        }
    }
}
