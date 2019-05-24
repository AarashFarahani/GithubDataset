package com.hackerrank.github.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CustomTimeStampSerializer extends StdSerializer<Timestamp> {
    private SimpleDateFormat formatter
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CustomTimeStampSerializer() {
        this(null);
    }

    public CustomTimeStampSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize (Timestamp value, JsonGenerator gen, SerializerProvider arg2)
            throws IOException, JsonProcessingException {
        gen.writeString(formatter.format(value));
    }
}
