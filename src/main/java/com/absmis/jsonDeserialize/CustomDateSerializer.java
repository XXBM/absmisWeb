package com.absmis.jsonDeserialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by dell on 2016/11/19.
 * 序列化
 */
public class CustomDateSerializer extends JsonSerializer<Calendar>{
    @Override
    public void serialize(Calendar value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeString(new SimpleDateFormat("yyyy-MM-dd").format(value.getTime()));
    }
}