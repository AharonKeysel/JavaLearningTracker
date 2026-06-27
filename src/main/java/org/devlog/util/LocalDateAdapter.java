package org.devlog.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAdapter
                                 implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {

    @Override
    public JsonElement serialize(
            LocalDate src,
            Type type,
            JsonSerializationContext ctx
    ) {
        return new JsonPrimitive(src.toString());
    }


    @Override
    public LocalDate deserialize(
            JsonElement json,
            Type type,
            JsonDeserializationContext ctx
    ) {
        return LocalDate.parse(json.getAsString());
    }

}
