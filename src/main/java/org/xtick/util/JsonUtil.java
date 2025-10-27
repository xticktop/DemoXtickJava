package org.xtick.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public class JsonUtil {
    private final static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();// 忽略json中在对象不存在对应属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);// 忽略空bean转json错误
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.getFactory().setStreamReadConstraints(StreamReadConstraints.builder().maxStringLength(40000000).build());
    }

    public static JsonNode toJsonObj(String content) throws JsonProcessingException {
        return mapper.readTree(content);
    }

    public static String toJson(Object obj) {
        if (Objects.isNull(obj)) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            log.error("obj to json error.", e);
        }
        return null;
    }


    public static String toJsonWithStrFormat(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (IOException e) {
            log.error("obj to json error.", e);
        }
        return null;
    }


    public static <T> T jsonToObj(String content, Class<T> clazz) {
        try {
            return mapper.readValue(content, clazz);
        } catch (Exception e) {
            log.error("Failed to Json to Object.content={}", content,e);
        }
        return null;
    }


    public static <T> List<T> jsonToList(String content, Class<T> clazz) {
        try {
            return mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            log.error("Failed to Json to Object.content={}",content, e);
        }
        return null;
    }

}
