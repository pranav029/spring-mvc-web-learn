package com.spring.app.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

public class SerializeUtil {

    public static <T extends Serializable> String toString(T data) throws IOException, IllegalAccessException {
        StringBuilder result = new StringBuilder("{\n");
        Field[] fields = data.getClass().getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            fields[i].setAccessible(true);
            Object value = fields[i].get(data);
            result.append("\t"+fields[i].getName() + ":"+ value + "\n");
        }
        result.append("}");
       return result.toString();
    }

    public static <T extends Serializable> String toString(List<T> data) throws IOException, IllegalAccessException {
        StringBuilder result = new StringBuilder("[\n");
        for(int j=0;j<data.size();j++){
            Field[] fields = data.get(j).getClass().getDeclaredFields();
            result.append(" {\n");
            for(int i=0;i<fields.length;i++){
                fields[i].setAccessible(true);
                Object value = fields[i].get(data.get(j));
                result.append("\t"+fields[i].getName() + ":"+ value + "\n");
            }
            if(j == data.size()-1)result.append(" }\n");
            else result.append(" },\n\n");
        }
        result.append("]");
        return result.toString();
    }
}
