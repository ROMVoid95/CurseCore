package de.erdbeerbaerlp.cfcore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public enum CFModStatus
{
    Unknown(-1),
    New(1),
    ChangesRequired(2),
    UnderSoftReview(3),
    Approved(4),
    Rejected(5),
    ChangesMade(6),
    Inactive(7),
    Abandoned(8),
    Deleted(9),
    UnderReview(10);

    public final int value;

    CFModStatus(int i)
    {
        this.value = i;
    }
    
    public static CFModStatus fromValue(int val) {
        for(CFModStatus type : CFModStatus.values()) {
            if(type.value == val) {
                return type;
            }
        }
        return CFModStatus.Unknown;
    }

    public static class ModStatusDeserializer implements JsonDeserializer<CFModStatus> {
        @Override
        public CFModStatus deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            int value = element.getAsInt();
            return CFModStatus.fromValue(value);
        }
    }
}
