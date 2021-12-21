package com.xuzimian.globaldemo.common.xml.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.core.ResolvableType;

import java.io.IOException;
import java.util.Optional;

public class CustomDateSerializer extends StdSerializer<PagedResponse> {

    public CustomDateSerializer() {
        this(null);
    }

    protected CustomDateSerializer(Class<PagedResponse> t) {
        super(t);
    }

    @Override
    public void serialize(PagedResponse value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("totalElements", String.valueOf(value.getTotalElements()));
        gen.writeStringField("totalPages", String.valueOf(value.getTotalPages()));
        gen.writeStringField("pageSize", String.valueOf(value.getPageSize()));
        gen.writeStringField("pageNumber", String.valueOf(value.getPageNumber()));
        gen.writeStringField("numberOfElements", String.valueOf(value.getNumberOfElements()));


        String listName, contentName;
        Optional objOpt = value.getContent().stream().findFirst();
        if (objOpt.isPresent()) {
            ResolvableType type = ResolvableType.forInstance(objOpt.get());
            contentName = type.resolve().getSimpleName().toLowerCase();
            listName = contentName + "s";
        } else {
            listName = "contents";
            contentName = "content";
        }

        gen.writeObjectFieldStart(listName);
        for (Object content : value.getContent()) {
            gen.writeObjectField(contentName, content);
        }
        gen.writeEndObject();

        gen.writeEndObject();

    }
}
