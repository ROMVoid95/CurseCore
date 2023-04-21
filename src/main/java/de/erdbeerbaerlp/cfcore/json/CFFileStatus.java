package de.erdbeerbaerlp.cfcore.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public enum CFFileStatus {
    Unknown(-1),
    Processing(1),
    ChangesRequired(2),
    UnderReview(3),
    Approved(4),
    Rejected(5),
    MalwareDetected(6),
    Deleted(7),
    Archived(8),
    Testing(9),
    Released(10),
    ReadyForReview(11),
    Depreciated(12),
    Baking(13),
    AwaitingPublishing(14),
    FailedPublishing(15);


    public int value;

    CFFileStatus(int i) {
        this.value = i;
    }
    public static CFFileStatus fromValue(int val) {
        for(CFFileStatus type : CFFileStatus.values()) {
            if(type.value == val) {
                return type;
            }
        }
        return CFFileStatus.Unknown;
    }

    public static class FileStatusDeserializer implements JsonDeserializer<CFFileStatus> {
        @Override
        public CFFileStatus deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            int value = element.getAsInt();
            return CFFileStatus.fromValue(value);
        }
    }
}