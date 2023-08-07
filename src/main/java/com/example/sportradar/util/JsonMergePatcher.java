package com.example.sportradar.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.mergepatch.JsonMergePatch;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Component
public class JsonMergePatcher {
    private final ObjectMapper mapper;

    public <T> T mergePatch(String json, @NotNull @Valid T target) {
        JsonNode patchedNode;
        try {
            JsonMergePatch patch = this.mapper.readValue(json, JsonMergePatch.class);
            patchedNode = patch.apply(this.mapper.convertValue(target, JsonNode.class));
        } catch (JsonPatchException | IOException var5) {
            throw new IllegalStateException(var5);
        }

        return (T) this.mapper.convertValue(patchedNode, target.getClass());
    }

    public JsonMergePatcher(final ObjectMapper mapper) {
        this.mapper = mapper;
    }
}
